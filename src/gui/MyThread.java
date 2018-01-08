package gui;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
/**
 * Creates a thread that listens to changes in the filePath given. 
 * @author Levi and Uriel
 *
 */
public class MyThread extends Thread{
	private String filePath;
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * Runs the thread and starts listening.
	 */
	public void run() {
		// TODO Auto-generated method stub
		WatchService watchService;
		try {
			watchService = FileSystems.getDefault().newWatchService();

			Path path = Paths.get(this.filePath);
			path.register(
					watchService, 
					StandardWatchEventKinds.ENTRY_CREATE, 
					StandardWatchEventKinds.ENTRY_DELETE, 
					StandardWatchEventKinds.ENTRY_MODIFY);

			WatchKey key;
			while ((key = watchService.take()) != null) {
				try {Thread.sleep(100);} catch (Exception e) { System.out.println(e.getMessage());}
				for (WatchEvent<?> event : key.pollEvents()) {
					System.out.println(
							"Event kind:" + event.kind() 
							+ ". File affected: " + event.context() + ".");
				}
				key.reset();
			}
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
}
