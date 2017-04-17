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
			JOptionPane.showMessageDialog(null, "目录不存在，请重新选择！", "提示", JOptionPane.WARNING_MESSAGE);
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
				basePanel.addResponse("开始读取已选择目录及子目录下的所有图片文件");
				
				ArrayList<File> allFiles = Utils.listAllFiles(IMG_PATH, new ArrayList<>());
				ArrayList<File> failed = new ArrayList<>();
				
				basePanel.addResponse("已读取所有图片文件，共 " + allFiles.size() + " 张\n");
				basePanel.addResponse("开始处理并解码图片文件，请耐心等待...");
				
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
						basePanel.addResponse("图片 " + originalFile.getName() + " 加载失败！");
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
						basePanel.addResponse("图片 " + originalFile.getName() + " 条形码解码失败！");
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
						basePanel.addResponse("已处理图片 " + originalFile.getName() + " 保存失败！");
						failure++;
						continue;
					}
					
					double pRate = (double)fileCounter / allFiles.size() * 100;
					basePanel.setProgress((int)pRate);
				}
				
				basePanel.addResponse("图片文件处理完成！\n");
				basePanel.addResponse("开始整理未识别图片文件...");
				
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
						basePanel.addResponse("未识别图片 " + source.getName() + " 保存失败！");
						continue;
					}
				}
				
				long tEnd = System.currentTimeMillis();
				long seconds = (tEnd - tStart) / 1000;
				double sRate = (allFiles.size() - failure) / (double)allFiles.size() * 100;
				
				basePanel.addResponse("未识别图片文件整理完成！\n");
				basePanel.addResponse("图片文件总数：" + allFiles.size());
				basePanel.addResponse("成功识别的图片数：" + (allFiles.size()-failure) );
				basePanel.addResponse("识别失败的图片数：：" + failure);
				basePanel.addResponse("识别正确率：" + sRate + "%");
				basePanel.addResponse("耗时：" + (seconds/60) + "分" + (seconds%60) + "秒");
				basePanel.setLoadDirEnabled(true);
			}
		}).start();
	}

}
