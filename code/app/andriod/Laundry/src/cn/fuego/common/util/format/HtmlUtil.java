package cn.fuego.common.util.format;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlUtil
{
	public static List<String> getImgStr(String htmlStr)
	{
		String img = "";
		Pattern p_image;
		Matcher m_image;
		List<String> pics = new ArrayList<String>();
		String regEx_img = "]*?>";
		p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
		m_image = p_image.matcher(htmlStr);
		while (m_image.find())
		{
			img = img + "," + m_image.group();
			Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)")
					.matcher(img);
			while (m.find())
			{
				pics.add(m.group(1));
			}
		}
		return pics;
	}
	
	public static String removeImg(String str)
	{
		String strPattern = "<img.+?/>|<img.+?</img>";
 
		return str.replaceAll(strPattern, "");
	}

}
