package org.lsz.simrpc.utils;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by lsz on 14-10-27.
 */
public class KryoKit {
    static Kryo kryo = new Kryo();
    public static byte[] serialize(Object object){
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(128);
        Output output = new Output(byteStream);
        kryo.writeObject(output, object);
        output.flush();
        return byteStream.toByteArray();
    }
    public static <T> T deserialize(byte bs[],Class<T> clazz){
        InputStream inputStream = new ByteArrayInputStream(bs);
        Input input = new Input(inputStream);
        return (T)kryo.readObject(input,clazz);
    }
}
