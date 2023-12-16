package com.mamay.task3.util;

import com.mamay.task3.exception.LogicException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class TextReader {

  public CharSequence readFromFile(String filename) throws LogicException {
    File file = returnFile(filename);
    if (file.exists()) {
      CharBuffer cbuf = null;
      try (FileInputStream input = new FileInputStream(file)) {
        FileChannel channel = input.getChannel();
        ByteBuffer bbuf = channel.map(FileChannel.MapMode.READ_ONLY, 0, (int) channel.size());
        cbuf = Charset.forName("8859_1").newDecoder().decode(bbuf);
      } catch (CharacterCodingException e) {
        log.error(e.getMessage());
      } catch (IOException e) {
        log.error(e.getMessage());
      }
      return cbuf;
    } else {
      throw new LogicException("Such file does not exist!");
    }
  }

  private File returnFile(String fileName) {
    URL resource = getClass().getClassLoader().getResource(fileName);
    if (resource == null) {
      throw new IllegalArgumentException("File not found");
    } else {
      return new File(resource.getFile());
    }
  }
}
