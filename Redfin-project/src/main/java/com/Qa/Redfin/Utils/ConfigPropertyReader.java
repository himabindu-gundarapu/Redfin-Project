package com.Qa.Redfin.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

	public class ConfigPropertyReader 
	{
		public static Properties props;
		public static Properties ReadProperty(String path) throws IOException 
		{
			props = new Properties();
			try {
				FileInputStream file = new FileInputStream(path);
				props.load(file);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return props;
			
		}
	}
