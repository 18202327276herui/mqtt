package usung.com.mqttclient.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

@SuppressWarnings("unchecked")
public class GsonHelper {

	private static Gson gson=null;
	
	@SuppressWarnings("rawtypes")
	public static Gson getGson(){
		if(gson == null){
			gson = new GsonBuilder().registerTypeAdapterFactory((TypeAdapterFactory) new NullStringToEmptyAdapterFactory()).create();
		}
		return gson;
	}
	
	public static class NullStringToEmptyAdapterFactory<T> implements TypeAdapterFactory {
	    @Override
		@SuppressWarnings("hiding")
		public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
			Class<T> rawType = (Class<T>) type.getRawType();
	        if (rawType != String.class) {
	            return null;
	        }
	        return (TypeAdapter<T>) new StringAdapter();
	    }
	}

	public static class StringAdapter extends TypeAdapter<String> {
	    @Override
		public String read(JsonReader reader) throws IOException {
	        if (reader.peek() == JsonToken.NULL) {
	            reader.nextNull();
	            return "";
	        }
	        return reader.nextString();
	    }
	    @Override
		public void write(JsonWriter writer, String value) throws IOException {
	        if (value == null) {
	            writer.nullValue();
	            return;
	        }
	        writer.value(value);
	    }
	}
}
