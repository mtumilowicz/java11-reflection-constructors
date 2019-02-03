import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by mtumilowicz on 2019-02-03.
 */
public class ConstructorReflectionTest {

    @Test
    public void getConstructors() {
        var constructors = Child.class.getConstructors();

        assertThat(constructors.length, is(1));

        var constructorsAsString = Arrays.toString(constructors);
        assertThat(constructorsAsString, containsString("public Child(java.lang.String,int)"));
    }

    @Test
    public void getDeclaredConstructors() {
        var constructors = Child.class.getDeclaredConstructors();

        assertThat(constructors.length, is(4));

        var constructorsAsString = Arrays.toString(constructors);
        assertThat(constructorsAsString, containsString("public Child(java.lang.String,int)"));
        assertThat(constructorsAsString, containsString("protected Child(int)"));
        assertThat(constructorsAsString, containsString("Child(java.lang.String)"));
        assertThat(constructorsAsString, containsString("private Child()"));
    }

    @Test(expected = NoSuchMethodException.class)
    public void getConstructor_notFound() throws NoSuchMethodException {
        Child.class.getConstructor(int.class, int.class);
    }

    @Test(expected = NoSuchMethodException.class)
    public void getConstructor_private() throws NoSuchMethodException {
        Child.class.getConstructor();
    }

    @Test(expected = NoSuchMethodException.class)
    public void getConstructor_fromParent() throws NoSuchMethodException {
        Child.class.getConstructor(String.class, String.class, String.class);
    }

    @Test
    public void getConstructor_public() throws NoSuchMethodException {
        assertThat(Child.class.getConstructor(String.class, int.class).toGenericString(),
                is("public Child(java.lang.String,int)"));
    }

    @Test(expected = NoSuchMethodException.class)
    public void getDeclaredConstructor_notFound() throws NoSuchMethodException {
        Child.class.getDeclaredConstructor(int.class, int.class);
    }

    @Test
    public void getDeclaredConstructor_private() throws NoSuchMethodException {
        assertThat(Child.class.getDeclaredConstructor().toGenericString(),
                is("private Child()"));
    }

    @Test
    public void getDeclaredConstructor_public() throws NoSuchMethodException {
        assertThat(Child.class.getDeclaredConstructor(String.class, int.class).toGenericString(),
                is("public Child(java.lang.String,int)"));
    }
}
