package de.pesacraft.lobbysystem.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * A class to bundle multiple files into one.<br>
 * You can specify the different files to compress, but also can decompress a
 * file to use its contents.
 *
 * @author FroznMine
 *
 */
public class FileBundle {
	/**
	 * Extract the given file.<br>
	 * Reads all files in the zipped file and returns them as a list of
	 * ByteArrayInputStream's.
	 *
	 * @param source
	 *            The file to read.
	 * @return A map containing ByteArrayInputStream's of the files connected to
	 *         their names in the compressed file
	 */
	public static Map<String, ByteArrayInputStream> unzipFile(final File source) {
		final Map<String, ByteArrayInputStream> inputStreams = new HashMap<String, ByteArrayInputStream>();

		final byte[] buffer = new byte[1024];

		try {
			// get the zip file content
			final ZipInputStream zis = new ZipInputStream(new FileInputStream(
					source));
			// get the zipped file list entry
			ZipEntry ze = zis.getNextEntry();

			while (ze != null) {

				final String fileName = ze.getName();

				final ByteArrayOutputStream os = new ByteArrayOutputStream();

				int len;
				while ((len = zis.read(buffer)) > 0)
					os.write(buffer, 0, len);

				final ByteArrayInputStream is = new ByteArrayInputStream(
						os.toByteArray());

				inputStreams.put(fileName, is);

				ze = zis.getNextEntry();
			}

			zis.closeEntry();
			zis.close();

		} catch (final IOException ex) {
			ex.printStackTrace();
		}
		return inputStreams;
	}

	/**
	 * Extract the given file by path.<br>
	 * Reads all files in the zipped file and returns them as a list of
	 * ByteArrayInputStream's.
	 *
	 * @param source
	 *            The path to the file to read.
	 * @return A map containing ByteArrayInputStream's of the files connected to
	 *         their names in the compressed file
	 */
	public static Map<String, ByteArrayInputStream> unzipFile(
			final String source) {
		return FileBundle.unzipFile(new File(source));
	}

	/**
	 * Zip the given input streams with the names into the given file.<br>
	 * The file specifies the location of storage.
	 *
	 * @param result
	 *            File where compressed file gets stored
	 * @param files
	 *            the filed that get zipped
	 */
	public static void zipFile(final File result,
			final Map<String, ? extends InputStream> files) {

		final byte[] buffer = new byte[1024];

		try {
			final FileOutputStream fos = new FileOutputStream(result);
			final ZipOutputStream zos = new ZipOutputStream(fos);

			for (final Entry<String, ? extends InputStream> file : files
					.entrySet()) {

				final ZipEntry ze = new ZipEntry(file.getKey());

				zos.putNextEntry(ze);

				final InputStream in = file.getValue();

				int len;
				while ((len = in.read(buffer)) > 0)
					zos.write(buffer, 0, len);

				in.close();
			}
			zos.closeEntry();
			// remember close it
			zos.close();

		} catch (final IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Zip the given input streams with the names into the given file.<br>
	 * The filename specifies the location of storage.
	 *
	 * @param result
	 *            path to where the file gets stored
	 * @param files
	 *            the filed that get zipped
	 */
	public static void zipFile(final String result,
			final Map<String, ? extends InputStream> files) {
		FileBundle.zipFile(new File(result), files);
	}
}
