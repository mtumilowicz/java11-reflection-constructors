[![Build Status](https://travis-ci.com/mtumilowicz/java11-reflection-constructors.svg?branch=master)](https://travis-ci.com/mtumilowicz/java11-reflection-constructors)

# java11-reflection-constructors
Using reflection to get information about the constructors of a class.

# preface
https://github.com/mtumilowicz/java11-reflection-executables

* `java.lang.reflect.Constructor` is used to represent method
* `Class` provides us with four methods to gather information
concerning constructors:
    * all constructors:
        * `Constructor[] getConstructors()` - 
        returns all public constructors
        * `Constructor[] getDeclaredConstructors()` - 
        returns all declared constructors
    * constructor by parameters
        * `Constructor getConstructor()` - 
            you can think of this method as a way of trying to 
            find a constructor in `getConstructors()` by its name and parameters 
            or throwing `NoSuchMethodException` (if the constructor 
            cannot be found)
        * `Constructor getDeclaredConstructor()` - 
            you can think of this method as a way of trying to 
            find a constructor in `getDeclaredConstructors()` by its name and parameters 
            or throwing `NoSuchMethodException` (if the constructor 
            cannot be found)
            
# project description
Class structure:
```
class Child extends Parent {
    private Child() {

    }

    Child(String name) {

    }

    protected Child(int count) {

    }

    public Child(String name, int count) {

    }
}


class Parent {
    public Parent() {

    }
    
    public Parent(String s1, String s2, String s3) {
        
    }
}
```

All tests are in `ConstructorReflectionTest`
* `getConstructors`
    ```
    var constructors = Child.class.getConstructors();
    
    assertThat(constructors.length, is(1));
    
    var constructorsAsString = Arrays.toString(constructors);
    assertThat(constructorsAsString, containsString("public Child(java.lang.String,int)"));
    ```
* `getDeclaredConstructors`
    ```
    var constructors = Child.class.getDeclaredConstructors();
    
    assertThat(constructors.length, is(4));
    
    var constructorsAsString = Arrays.toString(constructors);
    assertThat(constructorsAsString, containsString("public Child(java.lang.String,int)"));
    assertThat(constructorsAsString, containsString("protected Child(int)"));
    assertThat(constructorsAsString, containsString("Child(java.lang.String)"));
    assertThat(constructorsAsString, containsString("private Child()"));
    ```
* public constructor by params
    * not found - `NoSuchMethodException`
        ```
        @Test(expected = NoSuchMethodException.class)
        public void getConstructor_notFound() throws NoSuchMethodException {
            Child.class.getConstructor(int.class, int.class);
        }
        ```
    * not public - `NoSuchMethodException`
        ```
        @Test(expected = NoSuchMethodException.class)
        public void getConstructor_private() throws NoSuchMethodException {
            Child.class.getConstructor();
        }
        ```
    * from parent - `NoSuchMethodException`
        ```
        @Test(expected = NoSuchMethodException.class)
        public void getConstructor_fromParent() throws NoSuchMethodException {
            Child.class.getConstructor(String.class, String.class, String.class);
        }
        ```
    * public
        ```
        assertThat(Child.class.getConstructor(String.class, int.class).toGenericString(),
                is("public Child(java.lang.String,int)"));
        ```
* declared constructor by params
    * not found - `NoSuchMethodException`
        ```
        @Test(expected = NoSuchMethodException.class)
        public void getDeclaredConstructor_notFound() throws NoSuchMethodException {
            Child.class.getDeclaredConstructor(int.class, int.class);
        }
        ```
    * private
        ```
        assertThat(Child.class.getDeclaredConstructor().toGenericString(),
                is("private Child()"));
        ```
    * public
        ```
        assertThat(Child.class.getDeclaredConstructor(String.class, int.class).toGenericString(),
                is("public Child(java.lang.String,int)"));
        ```