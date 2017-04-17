package Abra;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.oned.MultiFormatOneDReader;

import Abra.view.AbraFrame;
import Abra.view.AbraPanel;
import Abra.view.ClientPanel;

public class Abra {
	
	private static String IMG_PATH = "";
	private static AbraFrame baseFrame;
	private static AbraPanel basePanel;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				if (UIManager.getLookAndFeel().isSupportedLookAndFeel()) {
					final String platform = UIManager.getSystemLookAndFeelClassName();
					if (!UIManager.getLookAndFeel().getName().equals(platform)) {
						try {
							UIManager.setLookAndFeel(platform);
						} catch (Exception exception) {}
					}
				}
				baseFrame = new AbraFrame();
				//basePanel = new AbraPanel("abra.png");
				//baseFrame.add(basePanel);
				baseFrame.setVisible(true);
			}
		});
		
    }
	
	public static boolean setPath(String path) {
		File imgPath = new File(path);
		if (!imgPath.exists()) {
			JOptionPane.showMessageDialog(null, "Ŀ¼�����ڣ�������ѡ��", "��ʾ", JOptionPane.WARNING_MESSAGE);
			return false;
		} else {
			IMG_PATH = path;
			return true;
		}
	}
	
	public static void process() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				basePanel.addResponse("��ʼ��ȡ��ѡ��Ŀ¼����Ŀ¼�µ�����ͼƬ�ļ�");
				
				ArrayList<File> allFiles = Utils.listAllFiles(IMG_PATH, new ArrayList<>());
				ArrayList<File> failed = new ArrayList<>();
				
				basePanel.addResponse("�Ѷ�ȡ����ͼƬ�ļ����� " + allFiles.size() + " ��\n");
				basePanel.addResponse("��ʼ��������ͼƬ�ļ��������ĵȴ�...");
				
				int fileCounter = 0;
				int failure = 0;
				long tStart = System.currentTimeMillis();
				
				Iterator<File> iterator = allFiles.iterator();
				
				while (iterator.hasNext()) {
					File originalFile = iterator.next();
					BufferedImage origin = null;
					BufferedImage image = null;
					fileCounter++;
					
					try {
						origin = ImageIO.read(originalFile);
						image = ImageProcess.crop(origin, origin.getWidth()/2, origin.getHeight()/2);
					} catch (IOException e) {
						basePanel.addResponse("ͼƬ " + originalFile.getName() + " ����ʧ�ܣ�");
						failure++;
						continue;
					}
					
					LuminanceSource source = new BufferedImageLuminanceSource(image);
					BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
					HashMap<DecodeHintType, Object> hints = new HashMap<>();
					hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
					MultiFormatOneDReader reader = new MultiFormatOneDReader(hints);			
					Result result = null;
					
					try {
						result = reader.decode(bitmap);
					} catch (Exception e) {
						basePanel.addResponse("ͼƬ " + originalFile.getName() + " ���������ʧ�ܣ�");
						failed.add(originalFile);
						failure++;
						continue;
					}
					
					try {
						File outputDir = new File(IMG_PATH + "\\processed\\");
						if (!outputDir.exists()) {
							outputDir.mkdirs();
						}
						File outputFile = new File(outputDir.getAbsolutePath() + "\\" + result.getText() + ".jpg");
						ImageIO.write(origin, "jpg", outputFile);
					} catch (IOException e) {
						basePanel.addResponse("�Ѵ���ͼƬ " + originalFile.getName() + " ����ʧ�ܣ�");
						failure++;
						continue;
					}
					
					double pRate = (double)fileCounter / allFiles.size() * 100;
					basePanel.setProgress((int)pRate);
				}
				
				basePanel.addResponse("ͼƬ�ļ�������ɣ�\n");
				basePanel.addResponse("��ʼ����δʶ��ͼƬ�ļ�...");
				
				Iterator<File> iteratorFailed = failed.iterator();
				
				while (iteratorFailed.hasNext()) {
					File source = iteratorFailed.next();
					
					try {
						File outputDir = new File(IMG_PATH + "\\failed\\");
						if (!outputDir.exists()) {
							outputDir.mkdirs();
						}
						File outputFile = new File(IMG_PATH + "\\failed\\" + source.getName());
						Files.copy(source.toPath(), outputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
					} catch (Exception e) {
						basePanel.addResponse("δʶ��ͼƬ " + source.getName() + " ����ʧ�ܣ�");
						continue;
					}
				}
				
				long tEnd = System.currentTimeMillis();
				long seconds = (tEnd - tStart) / 1000;
				double sRate = (allFiles.size() - failure) / (double)allFiles.size() * 100;
				
				basePanel.addResponse("δʶ��ͼƬ�ļ�������ɣ�\n");
				basePanel.addResponse("ͼƬ�ļ�������" + allFiles.size());
				basePanel.addResponse("�ɹ�ʶ���ͼƬ����" + (allFiles.size()-failure) );
				basePanel.addResponse("ʶ��ʧ�ܵ�ͼƬ������" + failure);
				basePanel.addResponse("ʶ����ȷ�ʣ�" + sRate + "%");
				basePanel.addResponse("��ʱ��" + (seconds/60) + "��" + (seconds%60) + "��");
				basePanel.setLoadDirEnabled(true);
			}
		}).start();
	}

}
