package uk.co.senab.bitmapcache;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;

public class Util {

	/**
	 * Pipe an InputStream to the given OutputStream
	 * 
	 * @param in
	 * @param out
	 * @throws IOException
	 */
	// TODO Can probably be optimized
	public static void pipe(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024];
		int len = in.read(buffer);
		while (len != -1) {
			out.write(buffer, 0, len);
			len = in.read(buffer);
		}
	}

	public static void saveBitmap(Bitmap bitmap, OutputStream out) {
		bitmap.compress(CompressFormat.PNG, 100, out);
	}

	public static String md5(String string) {
		try {
			// Create MD5 Hash
			MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
			digest.update(string.getBytes());
			
			byte messageDigest[] = digest.digest();

			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			for (int i = 0, z = messageDigest.length; i < z; i++) {
				hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

}