package com.cqx.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * redis序列化工具类
 * 当redis吧对象存到数据库中时候需要把对象序列化，提出时需要反序列化
 * Created by Shan on 2017/1/13.
 */
public class RedisObjectSerializer implements RedisSerializer<Object> {

    private Converter<Object, byte[]> serializer = new SerializingConverter();
    private Converter<byte[], Object> deserializer = new DeserializingConverter();

    static final byte[] EMPTY_ARRAY = new byte[0];

    /**
     * 序列化 对象转byte数组，用于吧byte数组用于存储到redis中
     *
     * @param o
     * @return
     * @throws SerializationException
     */
    @Override
    public byte[] serialize(Object o) throws SerializationException {
        if (o == null) {
            return EMPTY_ARRAY;
        }

        try {
            return serializer.convert(o);
        } catch (Exception ex) {
            return EMPTY_ARRAY;
        }
    }

    /**
     * 反序列化   byte数组转对象
     *
     * @param bytes
     * @return
     * @throws SerializationException
     */
    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (isEmpty(bytes)) {
            return null;
        }

        try {
            return deserializer.convert(bytes);
        } catch (Exception ex) {
            throw new SerializationException("Cannot deserialize", ex);
        }
    }

    private boolean isEmpty(byte[] data) {
        return (data == null || data.length == 0);
    }
}
