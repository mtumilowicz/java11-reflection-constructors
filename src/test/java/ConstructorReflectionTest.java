import org.junit.Test;

/**
 * Created by mtumilowicz on 2019-02-03.
 */
public class ConstructorReflectionTest {
    @Test
    public void getConstructors() {
        
    }
    
    @Test
    public void getDeclaredConstructors() {
        
    }
    
    @Test(expected = NoSuchMethodException.class)
    public void getConstructor_notFound() {
        
    }
    
    @Test(expected = NoSuchMethodException.class)
    public void getConstructor_private() {
        
    }
    
    @Test
    public void getConstructor_public() {
        
    }

    @Test(expected = NoSuchMethodException.class)
    public void getDeclaredConstructor_notFound() {

    }

    @Test
    public void getDeclaredConstructor_private() {

    }

    @Test
    public void getDeclaredConstructor_public() {

    }
}
