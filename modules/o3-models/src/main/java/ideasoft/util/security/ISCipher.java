//- Copyright Notice
// -----------------------------------------------------------------------
// (C) Copyright  1997 - 2000 IdeaSoft Uruguay S.R.L.  All Rights Reserved
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF IdeaSoft Co.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
// $Id: ISCipher.java,v 1.2 2005/10/07 13:50:51 ameyer Exp $
// -----------------------------------------------------------------------
package ideasoft.util.security;

import com.ideasoft.app.ContextUtils;
import ideasoft.util.log.Log;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Provider;
import java.security.Security;

public class ISCipher {

  //- Private instance members
  private Cipher decCipher = null;

  private Cipher encCipher = null;

  private SecretKey desKey = null;

  //- Public class members
  public static String DEFAULT_KEY_PATH = ContextUtils.getFullPath("config/key");

  public static String DEFAULT_SEC_PATH = ContextUtils.getFullPath("config/sec");

  public static final byte[] DEFAULT_KEY =
      new byte[]{(byte) 0x19, (byte) 0x83, (byte) 0xC7, (byte) 0x32, (byte) 0x25, (byte) 0x89, (byte) 0x76, (byte) 0x92};

  private static boolean USE_SUN_PROVIDER = ContextUtils.getBooleanProperty("iscipher.use.sun.provider", false);

  private static boolean defaultKey = false;

  private static final String ALGORITHM = "DES/ECB/PKCS5Padding";

  private static ISCipher instance = null;

  //- Private methods
  private ISCipher() {
  }

  public String dec(byte[] text) {
    if (text == null) {
      Log.warning("ISCipher: can't decrypt a null text");
      return null;
    }

    try {
      synchronized (this) {
        // Create the cipher
        if (decCipher == null) {
          decCipher = Cipher.getInstance(ALGORITHM);
          decCipher.init(Cipher.DECRYPT_MODE, desKey);
        }
        // Decrypt the ciphertext, please note that Cipher is not thread-safe
        byte[] decText = decCipher.doFinal(text);
        return new String(decText);
      }
    } catch (GeneralSecurityException se) {
      String msg = "The text couldn't be decrypted " + se;
      Log.error(msg);
      throw new RuntimeException(msg + ". " + se.getMessage());
    }
  }

  public byte[] enc(String text) {
    if (text == null) {
      Log.warning("ISCipher: can't encrypt a null text");
      return null;
    }

    try {
      synchronized (this) {
        // Create the cipher
        if (encCipher == null) {
          encCipher = Cipher.getInstance(ALGORITHM);
          encCipher.init(Cipher.ENCRYPT_MODE, desKey);
        }
        // Encrypt the text, please note that Cipher is not thread-safe
        byte[] encText = encCipher.doFinal(text.getBytes());
        return encText;
      }
    } catch (GeneralSecurityException se) {
      String msg = "The text couldn�t be encrypted " + se;
      Log.error(msg);
      throw new RuntimeException(msg + ". " + se.getMessage());
    }
  }

  /**
   * This method first encode the string argument using the GCipher class and the returned array is encoded using base64. This method may be
   * used in conjunction with @link ServerCubeConfig#decodeBase64(String)
   *
   * @param plainStr the plain String to be encoded.
   * @return the encoded string
   * @see ISCipher#decBase64(String) method
   */
  public String encBase64(String plainStr) {
    byte[] encBytes = enc(plainStr);

    byte[] encBytes64 = Base64.encodeBase64URLSafe(encBytes);

    return new String(encBytes64);
  }

  /**
   * This method first decode the base64 String encoded argument and then use the GCipher class to decode the returned array. This method
   * may be used in conjunction with @link ServerCubeConfig#encodeBase64(String)
   *
   * @param enc64Str the base64 encoding string
   * @throws DecoderException if the argument is not a valid base64 enoced string
   * @return the plain string
   * @see ISCipher#encBase64(String) method
   */
  public String decBase64(String enc64Str) throws DecoderException {
    if (!Base64.isArrayByteBase64(enc64Str.getBytes())) {
      throw new DecoderException("Can't decode. The input String is not a Base64 encoding String.");
    }

    byte[] encBytes = Base64.decodeBase64(enc64Str.getBytes());

    String decText = dec(encBytes);

    return decText;
  }

  private void loadKey(String keyPath) {
    try {
      byte[] encoded = new byte[8];
      FileInputStream in = new FileInputStream(keyPath);
      in.read(encoded);
      in.close();
      init(encoded);
    } catch (IOException ioe) {
      String msg = "The key file " + keyPath + " couldn�t be found";
      Log.error(msg);
      Log.debug(ioe);
      throw new RuntimeException(msg + ". " + ioe.getMessage());
    }
  }

  private void init(byte[] encoded) {
    try {
      if (USE_SUN_PROVIDER) {
        Class providerClass = Class.forName("com.sun.crypto.provider.SunJCE");
        Security.addProvider((Provider) providerClass.newInstance());
      }

      DESKeySpec spec = new DESKeySpec(encoded);
      SecretKeyFactory sf = SecretKeyFactory.getInstance("DES");
      desKey = sf.generateSecret(spec);
    } catch (InstantiationException se) {
      String msg = "The cipher couldn�t be initialazed " + se;
      Log.error(msg);
      Log.debug(se);
      throw new RuntimeException(msg + ". " + se.getMessage());
    } catch (IllegalAccessException se) {
      String msg = "The cipher couldn�t be initialazed " + se;
      Log.error(msg);
      Log.debug(se);
      throw new RuntimeException(msg + ". " + se.getMessage());
    } catch (ClassNotFoundException se) {
      String msg = "The cipher couldn�t be initialazed " + se;
      Log.error(msg);
      Log.debug(se);
      throw new RuntimeException(msg + ". " + se.getMessage());
    } catch (GeneralSecurityException se) {
      String msg = "The cipher couldn�t be initialazed " + se;
      Log.error(msg);
      Log.debug(se);
      throw new RuntimeException(msg + ". " + se.getMessage());
    }

  }

  public static ISCipher getInstance() {
    return getInstance(DEFAULT_KEY_PATH, true);
  }

  public static ISCipher getInstance(String keyPath) {
    return getInstance(keyPath, false);
  }

  protected static synchronized ISCipher getInstance(String keyPath, boolean withDefKey) {
    if (instance == null || defaultKey != withDefKey) {
      defaultKey = withDefKey;
      instance = new ISCipher();
      if (defaultKey) {
        instance.init(DEFAULT_KEY);
      } else {
        instance.loadKey(keyPath);
      }
    }
    return instance;
  }

  public static byte[] readSecFile() {
    return readSecFile(DEFAULT_SEC_PATH);
  }

  public static byte[] readSecFile(String secPath) {
    try {
      File f = new File(secPath);
      if (!f.exists()) {
        Log.warning("The sec file " + secPath + " not exist");
        return null;
      }
      FileInputStream in = new FileInputStream(f);
      byte[] sec = new byte[in.available()];
      in.read(sec);
      in.close();
      return sec;
    } catch (IOException ioe) {
      String msg = "The sec file " + secPath + " couldn�t be read";
      Log.error(msg);
      throw new RuntimeException(msg + ". " + ioe.getMessage());
    }
  }

  public static void writeSecFile(byte[] sec) {
    writeSecFile(sec, DEFAULT_SEC_PATH);
  }

  public static void writeSecFile(byte[] sec, String secPath) {
    try {
      FileOutputStream out = new FileOutputStream(secPath);
      out.write(sec);
      out.close();
    } catch (IOException ioe) {
      String msg = "The sec file " + secPath + " couldn�t be saved";
      Log.error(msg);
      throw new RuntimeException(msg + ". " + ioe.getMessage());
    }
  }
}