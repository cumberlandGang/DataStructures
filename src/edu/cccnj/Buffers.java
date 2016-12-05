package edu.cccnj;

// Sorry for the essay
// A lot of the comments can be skipped, but make sure to read the Design Notes at the bottom.

import java.util.ArrayList;

/**
 * Buffers are data structures which allow information to be stored in some order. The term buffers isn't used for this
 * purpose traditionally, but it seems that there is also no word to describe this "kind" (in the type theory sense) of
 * Data Structure.
 *
 * The two most studied buffers are Stacks and Queues. A stack is LIFO data structure (Last in, First out), whereas
 * a Queue is FIFO (First in, Last out). In a high-level language such as Java, structures like this aren't actually
 * very common. However, diving deeper into both programming and computing reveals that these structures are quite
 * prolific.
 *
 * The way Java works, is that it compiles code into Bytecode, which runs on something called a JRE (Java Runtime Environment)
 *
 * The JRE is a virtual machine. That is, you are really simulating a computer within your computer, which happens to be
 * designed to run Java code. The point is that every system that has a JRE will be able to run the code, so you have to
 * port the JRE for every new Operating System, rather than porting every program ever made in Java for the system.
 *
 * But how does the JRE really interact with the system? Simply put, the JRE is a JIT (Just-in-time) compiler. Meaning
 * that it runs your bytecode, which is translated from a Java file, and then dynamically generates machine code as it
 * reads the bytecode.
 *
 * Machine code is the only language the computer can understand. It is strings of 0's and 1's that the processor is
 * hardcoded (with a light drill) to understand. The machine code is run by the processor, which can only do a few things.
 *
 * One of the primary purposes of the processor is to keep track of a "stack pointer", which tells the processor what is
 * going to be executed after the given subroutine (equivalent to a Java method, in many ways). So, all computers are examples
 * of a "Stack Machine". This means that every single millisecond your computer is on, it's manipulating its own personal
 * Stack at pretty damn near the speed of light.
 *
 * So, stacks are ubiquitous, and arguably enable modern life as we know it. What cool facts are there about Queues?
 *
 * It's what British people say when they mean "line". Silly Brits.
 * @author natepisarski, rypriopre
 */
public class Buffers{

    /**
     * Used for polymorphic purposes in the Buffers convneince methods.
     * Using an interface for the sole purposes of Subtyping is referred to as
     * "Marker Interface Pattern", one of thousands of software design patterns common in the world.
     */
    public interface Buffer {

    }

    /**
     * The stack class. Like most of these data structures, it's generic...
     * But, unlike our LinkedList, it can accept any object. The thing about our LinkedList, is it needed advanced
     * features (like map, filter, reduce, etc), so we had to use what's called a type variable (T). The type variable
     * makes sure that we can have any type in the list, but using the type variable makes sure that whatever type is in
     * the list, that ALL ITEMS IN THE LIST HAVE THE SAME TYPE.
     *
     * In our Stack, we actually don't care what kind of data we have in it. So we get to use an "Unbounded Wildcard" type.
     *
     * In Java, ? is the unbounded wildcard. If you need to to implement a certain interface, or class, you can use a "bounded wildcard"
     * like this:
     *
     * thing<? extends x>
     *
     * Ours is just ?. Which, to be fair, is actually bounded as <? extends Object>, which is everything in Java.
     *
     * It's better to use ? than Object in a generic class because it shows we can accept ANYTHING, not things that
     * are just strictly java.lang.Object. It also prevents the need to type-cast when you get objects back from the list
     *
    ....
    ....
    ....



    ……………………………………..________
………………………………,.-‘”……………….“~.,
………………………..,.-“……………………………..”-.,
…………………….,/………………………………………..”:,
…………………,?………………………………………………\,
………………./…………………………………………………..,}
……………../………………………………………………,:`^`..}
……………/……………………………………………,:”………/
…………..?…..__…………………………………..:`………../
…………./__.(…..”~-,_…………………………,:`………./
………../(_….”~,_……..”~,_………………..,:`…….._/
……….{.._$;_……”=,_…….”-,_…….,.-~-,},.~”;/….}
………..((…..*~_…….”=-._……”;,,./`…./”…………../
…,,,___.\`~,……”~.,………………..`…..}…………../
…………(….`=-,,…….`……………………(……;_,,-”
…………/.`~,……`-………………………….\……/\
………….\`~.*-,……………………………….|,./…..\,__
,,_……….}.>-._\……………………………..|…………..`=~-,
…..`=~-,_\_……`\,……………………………\
……………….`=~-,,.\,………………………….\
…………………………..`:,,………………………`\…………..__
……………………………….`=-,……………….,%`>–==“
…………………………………._\……….._,-%…….`\
……………………………..,<`.._|_,-&“…………….`

With that in mind, let's take a moment to appreciate that wildcards in JAVA only like to work when supplying type variables,
not when declaring classes. We're stuck using Object. I discovered this after my original comment.

ArrayList<?>.add(new Object()) is literally a compile error, and theoretically there is no way in holy heck that it should
be. C# handles it fine with dynamic, their version of wildcards. Haskell handles it with Partial Signatures. C++ can
probably even do it.

java--
     */
    public static class Stack implements Buffer{

        /**
         * The actual contents of the stack
         */
        private ArrayList<Object> contents;

        /**
         * Initialize the stack.
         */
        public Stack() {
            contents = new ArrayList<>();
        }

        /**
         * Stacks are LIFO. So, the most recent item in the stack will be returned when the Stack is accessed.
         * Barring this in mind, push will put the object at the back, and pop will take it from the back.
         * @param obj
         */
        public void push(Object obj) {
            contents.add(obj);
        }

        /**
         * Pop is the other half of Stack. It will remove the most recent item in the stack, and return it.
         * @return The last item of the stack
         */
        public Object pop() {

            // If there's nothing in the list, we have to return null or throw an exception. This is just easier for now.
            if(contents.size() == 0)
                return null;

            // Get the last item
            Object item = contents.get(contents.size() - 1);

            // Take the last item off
            contents.remove(contents.size() - 1);

            return item;
        }
    }

    /**
     * The queue is another essential data structure, albeit less important than Stack. It's FIFO, meaning that it
     * works exaclty like a line at the bank would work
     */
    public static class Queue implements Buffer {

        /**
         * The contents of the Queue
         */
        private ArrayList<Object> contents;

        /**
         * Constructor for the queue, will just initialize the instance variables.
         */
        public Queue() {
            contents = new ArrayList<>();
        }

        /**
         * Places an item in the queue. Since Queue is FIFO, this will actually put it at the end of the list,
         * but when we get an item from the list, it takes it from the front.
         * @param item The item to put on the list.
         */
        public void enqueue(Object item) {
            contents.add(item);
        }

        /**
         * Removes an item from the queue, returning it
         * @return The item in the front of the Queue, since this is FIFO
         */
        public Object dequeue() {

            // Nothing in the queue? Returning null here prevents an ArrayIndexException later on
            if(contents.size() == 0)
                return null;

            Object item = contents.get(0);
            contents.remove(0);

            return item;
        }
    }

    /*
     * Since both of these classes are internal, we can put some convenience methods in their parent class to
     * make initializing them easier.
     */

    /**
     * References a type of Buffer from this file
     */
    public enum Types {
        Queue,
        Stack
    }

    /**
     * Creates a new Buffer. The Buffer can either be a Queue or a Stack. It also provides
     * some data that will be placed in the data structure.
     * @param bufferType The type of the structure
     * @param objects The objects to place into the queue
     * @return
     */
    public static Buffer make(Types bufferType, Object... objects) {

        // The product we're going to return
        Buffer product = null;

        switch(bufferType){

            // The user wants a Queue
            case Queue:

                product = new Buffers.Queue();
                for(Object i : objects) // Add all the objects to the Queue
                    ((Queue)product).enqueue(i);
                break;

            case Stack:
                product = new Stack();
                for(Object i: objects) // Add all the objects to the Stack
                    ((Stack)product).push(i);
                break;
        }

        return product;
    }

    /**
     * Makes an empty Buffer
     * @param bufferType The type of Buffer (Stack or Queue) you want to make
     * @return The empty Buffer
     */
    public static Buffer make(Types bufferType) {

        // We can use just an if-else because there are only two possible types
        if(bufferType == Types.Queue)
            return new Queue();
        else
            return new Stack();
    }
}

/*
----------DESIGN NOTES----------
Why is this file designed the way it is? Is there a better way to do it?

In large software projects, the overall design of the code is far more important than the code itself. Code can always
change. Design, on the other hand, cannot always. Once you make something a certain way, and you or other programmers
decide to use it in other places in the project, designing the program in a different way becomes very time consuming,
bug-introducing, and expensive.

So, let's examine the design of this file. What can we garner about how this thing is built?

Well, first off, there is a class which contains two inner classes: Queue and Stack. Primarily, this is because
I didn't want to make a bunch of different files, and Java doesn't let you declare more than once class per file
unless they're inner classes. Speaking of poor design...

Anyway, we use an interface which doesn't have any methods to categorize the different data structures. This lets us
return both a Queue or a Stack from Buffers.make(). This is a valid use of an interface, called a Marker Interface.

Now, is this the best design? Absolutely not. Before reading the "proper" design of the file, think about how you might
restructure this code to make it easier to use.

----------------------------------

The best design that I can think of to make these structures better would be:

* Have two different files, one for Queue, one for Stack.
* Keep the interface, but include an "add" and "get" method, which Stack and Queue would overload to do their
      enqueue / dequeue, or pop / push.
* Have another separate file, BufferFactory, to have the Factory make method, if it's deemed useful. In most cases
      it is not.

----------------------------------

Software design is important. All good programmers eventually stop being programmers, and become strictly Technical Leads,
Software Engineers, etc. whose entire job description is software design. They usually make well over $200K/year, because
very few have an eye for proper design.

Software Design has made some projects, and broken some projects. Eclipse, Firefox, Google Chrome, and Microsoft Office
all had insanely good Software Design, so they got ahead in their industry.

When I started programming, Weissman would say "You need to think about the code for twice as long as it takes to write it".
This was perplexing, because many times I would just jump straight into coding. Now, I follow that rule he gave pretty
strongly. As your grow as a programmer, the "why" becomes vastly more important than the "how".
 */