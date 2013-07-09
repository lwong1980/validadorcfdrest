package org.common.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * A utility class for working with base64 encoding.
 */
public class Base64Binary {
	private static final Logger logger = Logger.getLogger(Base64Binary.class);
  /**
   * Creates a clone of the byte array <code>pValue</code>.
   */
  public static byte[] getClone(byte[] pValue) {
    byte[] result = new byte[pValue.length];
    System.arraycopy(pValue, 0, result, 0, pValue.length);
    return result;
  }

  /**
   * Converts the string <code>pValue</code> into a base64 encoded byte array.
   */
  public static byte[] decode(String pValue) throws IOException {
    return (new BASE64Decoder()).decodeBuffer(pValue);
  }

  /**
   * Converts the base64 encoded byte array <code>pValue</code> into a string.
   */
  public static String encode(byte[] pValue) {
    return (new BASE64Encoder()).encode(pValue);
  }

  // Returns the contents of the file in a byte array.
  public static byte[] getBytesFromFile(File file) throws IOException {
    InputStream is = new FileInputStream(file);

    // Get the size of the file
    long length = file.length();

    // You cannot create an array using a long type.
    // It needs to be an int type.
    // Before converting to an int type, check
    // to ensure that file is not larger than Integer.MAX_VALUE.
    if (length > Integer.MAX_VALUE) {
      // File is too large
    }

    // Create the byte array to hold the data
    byte[] bytes = new byte[(int) length];

    // Read in the bytes
    int offset = 0;
    int numRead = 0;
    while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
      offset += numRead;
    }

    // Ensure all the bytes have been read in
    if (offset < bytes.length) {
      throw new IOException("Could not completely read file " + file.getName());
    }

    // Close the input stream and return bytes
    is.close();
    return bytes;
  }

  public static byte[] getBytesFromString(String string) throws Exception {
    byte[] bytes = string.getBytes("UTF-8");
    return bytes;
  }
  
  public static byte[] getFileDecompressed(byte[] compressedData) throws Exception {
	  	 long initializationTime = System.currentTimeMillis();
	  	 logger.debug("Inicia decompress "+initializationTime );
	  	// Create the decompressor and give it the data to compress
		 Inflater decompressor = new Inflater();
		 decompressor.setInput(compressedData);
		 // Create an expandable byte array to hold the decompressed data
		 ByteArrayOutputStream bos = new ByteArrayOutputStream(compressedData.length);
		 // Decompress the data
		 byte[] buf = new byte[1024];
		 while (!decompressor.finished()) {
		     try {
		         int count = decompressor.inflate(buf);
		         bos.write(buf, 0, count);
		     } catch (DataFormatException e) {
		     }
		 }
		 try {
		     bos.close();
		 } catch (IOException e) {
		 }

		 // Get the decompressed data
		 byte[] decompressedData = bos.toByteArray();
		 // Decode Base64
		 String base64String = new String(decompressedData);
		 //Move in a byte
		 byte[] normalizedFile = Base64Binary.decode(base64String);
		 //
		 logger.debug("Termina decompress "+System.currentTimeMillis());
		 logger.debug("Total = " + (((System.currentTimeMillis() - initializationTime))/1000)/60);
		 return normalizedFile;
	  
  }
  

  
  
  public static byte[] zipFiles(String directory, String[] files) throws IOException {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ZipOutputStream zos = new ZipOutputStream(baos);
      byte bytes[] = new byte[1024];

      for (int i = 0; i <  files.length ; i ++) {
    	  logger.debug( "Directory : " +directory);
    	  logger.debug( files[i]);
          FileInputStream fis = new FileInputStream(directory + files[i]);
          BufferedInputStream bis = new BufferedInputStream(fis);

          zos.putNextEntry(new ZipEntry(files[i]));

          int bytesRead;
          while ((bytesRead = bis.read(bytes)) != -1) {
              zos.write(bytes, 0, bytesRead);
          }
          zos.closeEntry();
          bis.close();
          fis.close();
      }
      zos.flush();
      baos.flush();
      zos.close();
      baos.close();

      return baos.toByteArray();
  }
  
 
  
}