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
}
```

all tests are in 