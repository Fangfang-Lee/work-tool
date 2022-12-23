package base64;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.stream.Collectors;

public class Base64Util {

    public static void main(String[] args) throws IOException {
//        String str = "AAAAAAEAAAAAOjAAAAgAAAB1A2IAdgNdAHcDXgB4A1AAeQNfAHoDSQB7A14AfAM7AEgAAAAOAQAAygEAAIgCAAAqAwAA6gMAAH4EAAA8BQAAzAOuBHMK6AxlD7gPzRQJHLEe5x6XIpsmTCdWL9svaTBVNpY7yj6CP7VCQ0mpT69PilNMVWlVVVkgXClcN14TX+xhTme+Z0Rph2kiaq5rQG3/cMV4THrlgH6BlYHOgZmGvYjHjWuRrpH5lbeW85eDmNSbUZ6NnhOhXqGlo/ijDabkp/GqjauDr9mwx7I+s2K0vbYEuN3H2s7L0bTSGtR41vjWfdiX2TXgRuF74aDkv+X76YvuBe8S9EP2o/Zr94X35/d7/ZH93wQOBV4JIguYC+YLEA4XEfwTcxSnFC0VYBa9GtQbQCNnJHcqjDjaOV89vz2DPvc+9z8wQI1GTUgoSUZKK0xPVENX6lcgXtheZWYnaCloV2wvcB11VncseOh8pH0uf6yMro5bj3uPDZPhlQGZ4JkKmiWhs6Pyo/2uF693sOaxuLInszC11rYNt/+4xbnvvDi/2MMKxCnEssWUyNXQJdFz25vbI99Y30/h0+ea6dvqDexn7XnuuvFl9p/2avdZBzQJRQuPDXARyxEXEhoUERWAGGQcUx/jIJYh5Sm9LAsyHDL8NAU21jd/OEE5RT1qPSdCuEdrSVlUflZSWo9cx18YYF9jXWRfZLdncGxUbZVtE3VAetWHEYjCinaOjY6kkSOVk5Z9nAihw6EloriivqPwqBqqq62jsriz/bNOtHO3ILjBuGm5sbqnxHvLDM1dzwXSaNNy0wzUANjI2vnay9/N30bguenN6UPqUe3t7kXx8fJQ9+v4I/3j/ab/tAJKBEIM8g1dDxASCBrzH/ojTSY1Kt8qli3/LQww4TZcODM96j3EQ/BFiEfYTw9SFlTfVBJWv1Z8WENbg2YiavFqhm8jdCF1S3jjfDJ9CoPokwiYgJ5hn1ulzqZRp0uwkbBZtjm7ObxgvQ/DpMQXx1bH7sdLyaPKas2yzpzUxNT11YTWb9js2ZfdUt+e4RPkguSv5Tzqgeqn6w3wP/KL9FT1IwKfA9cHeQhQCaALjQz3DS8S0RJEFAIXRhmkHQQhKyWwKaorZi3RL2ozpzTSNFo3wzd6OaA9tkaSSn5NKU5wUmRVl1iVWXpcrWG8YnplQGpHbk5uonD5cb1yb3bTfYGA74DPgtKCdYMniaaMA42OjaGNg484kQmTwppGmzSeQJ+hny2jcKMXpH2lo6str/+x/rNHtTy2/bb0udC6qLvVwArGxdKa0x3XTd/A4V/lZufB6JHrw+4977Xv5e8E8UP/uQCiAgsJNwxeDvQOyhLXHiYglSDXIjsluCcnKmUzaziDOZQ7Mz9IQqZCGUNeRM1E2lV0VqZdhmRMaD1u6HXWeQt6HXp8ehx8jofriW2LwY8Nkq6V25Yzl66Y3ZogngmfIZ+3n5emp6zZrAO5770KwHfB58ICxL/IOMwIz7XandxN3bLdUd/f5RrmHOir6rjs5+9N9V4BrQEeBYIHKA4xEAwRPxJME+gV+xflHBUeHyRKJIkkzCXIJrUtDC6mL5YxozYEOLE750EaQnJCnkZ+SWJLmUw5TtJR7VUeXEthvWGBY7dnfHHCcTFz6XUieJh53XkCfCN8n4NShH6FVInEi3yOtI4Yj1iPhY+skbOX1pePnomfBaLKouyiTaPEpN2kS6VpqK+vILDLsQ+zprMWtR3N99Dx05jWnNfP2APaxdvu3w3gF+kn66zrzfMA9/771f7EA+QHwA9BEFcQzBGiE6AcESCIJqIoAyv8MJ8yvjLVO+4+AERnRRpKXkojTDtN+E46V05aNlx3XT9f8GBdYqdnp2j/acNq02vpa91shXDZcuV1NHe3d9V3H3rHfRR/GH+VgPCDZYU/hr+NqZD3k8OV9ZtynUWeaaE=";
//        String decoder = Base64Util.decoder(str);
//        System.out.println(decoder);

        String str = "\\xE4\\xB8\\x8A\\xE8\\xA1\\xA3";
        System.out.println(byteToString(str));
    }

    public static String decoder(String str) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64.getDecoder().decode(str));
        InputStreamReader input = new InputStreamReader(byteArrayInputStream);

        BufferedReader bf = new BufferedReader(input);

        String line = null;

        StringBuilder sb = new StringBuilder();

        while((line=bf.readLine()) != null){

            sb.append(line);

        }

        return  sb.toString();
    }

    public static String byteToString(String str) throws UnsupportedEncodingException {
        String s1 = str.replaceAll("\\\\x", "%");
        String decode = URLDecoder.decode(s1, "utf-8");
        return decode;
    }
}
