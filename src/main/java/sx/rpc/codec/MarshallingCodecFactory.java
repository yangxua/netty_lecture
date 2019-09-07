package sx.rpc.codec;

import org.jboss.marshalling.*;

import java.io.IOException;

/**
 * @Auther: allanyang
 * @Date: 2019/7/10 14:50
 * @Description:
 */
public final class MarshallingCodecFactory {

    public static Marshaller buildMarshalling() throws IOException {
         final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
         final MarshallingConfiguration configuration = new MarshallingConfiguration();
         configuration.setVersion(5);
         return marshallerFactory.createMarshaller(configuration);
    }

    public static Unmarshaller buildUnMarshalling() throws IOException{
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        return marshallerFactory.createUnmarshaller(configuration);
    }
}