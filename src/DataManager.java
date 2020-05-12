import java.util.ArrayList;

public class DataManager {
	public ArrayList<File> Files = new ArrayList<File>();
	private boolean startindex;
	private int chsize = 0;
	int fileId = -1;
	private int freememory = Block.n;

	public void CreateFile(int size) {
		startindex = true;
		chsize = 0;
		fileId = Files.size();
		if (size > freememory) {
			Main.textAreaWindow.append("Невозможно добавить файл! \n");
			startindex = false;
			return;
		}
		File file = new File(fileId, size);
		for (int i = 0; i <= Block.n; i++) {
			if (size > chsize) {
				if (Block.memory.get(i).getId() != -1) {
					while (Block.memory.get(i).getId() != -1) {
						i++;
					}
				}
				file.addKnot(Block.memory.get(i));
				freememory--;
				chsize++;
			}
			if (size == chsize) {
				Files.add(fileId, file);
				Main.textAreaWindow.append("Добавлен файл № " + fileId + "\n");
				Main.textAreaWindow.append("Свободно памяти: " + freememory + "\n");
				return;
			}
		}
	}
	public void DeleteFile(int id) {
		if (Files.get(id) == null) {
			Main.textAreaWindow.append("Данного файла не сущетвует! \n");
		} else {
			freememory = freememory + Files.get(id).fileSize();
			Files.get(id).removeKnots();
			Main.textAreaWindow.append("Удалён файл " + Main.textFieldId.getText() + "\n");
		}
		Main.textAreaWindow.append("Свободно памяти: " + freememory + "\n");
	}
}