package edu.cccnj;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * The base class for collections which act like LinkedLists.
 * @author natepisarski, rypriore
 */
public class LinkedList<T> {

    /**
     * The current Node of the Linked List. This is the data that the list holds. In array terms, take for example:
     * int a[] = {1, 2, 3, 4, 5}
     *
     * If this Node is the Head of the list, currentNode would be 1.
     */
    private T currentNode;

    /**
     * A reference to the rest of the LinkedList. Notice that the type of this variable is the same as the class we're
     * in. Thus, it is a recursive data structure. The methods we implement must work in a recursive fashion in order
     * to manipulate the list.
     *
     * Since it's recursive, for each item in the list, it contains a reference to the rest of the list after it. So
     * while an array may look like
     *
     * [1, 2, 3, 4, 5]
     *
     * A LinkedList looks more like
     *
     * [1, [2, [3, [4, [5]]]]]
     */
    private LinkedList<T> rest;

    /**
     * Rest is Null if this is the end of the list. It will always start out this way.
     */
    public LinkedList() {
        rest = null;
    }

    /**
     * This constructor sets what the current node is equal to.
     * @param currentNode The current node of the class. See documentation on currentNode.
     */
    public LinkedList(T currentNode) {
        this();
        this.currentNode = currentNode;
    }

    /**
     * Puts the item at the end of the entire LinkedList structure. While this may seem obvious, consider:
     *
     * Every node holds a reference to the rest of the list. So, any node will also have the append(T) method.
     *
     * The approach here is to find the end of the list, and make the node there. Each time the function runs it should
     * be doing these checks. This is a recursive method.
     * @param item The item you want at the end of the list.
     */
    public void append(T item) {

        // If, for some reason, the list was created with a no-argument constructor, we just append it here
        if(currentNode == null)
            currentNode = item;

        // We're at the end of the list, since rest is null. Make rest a new LinkedList where currentNode is the item
        else if(rest == null)
            rest = new LinkedList<T>(item);

        // We're not at the end. rest is a LinkedList. Pass of the method call to that LinkedList instead of this one.
        else
            rest.append(item);
    }

    /**
     * Gets an item from the LinkedList. This works just how ArrayList<T>.get() would work.
     *
     * Indexing will begin at 0. For an index that is greater than the size of the list, null will be returned.
     * @param index The index of the item in the list
     * @return The item found at the index
     */
    public T get(int index){
        /*
        Strategy: Since LinkedLists are recursive, we have to... recurse. Kind of a head scratcher there.

        So, you have to consider what every single step of this function is going to be doing every time it runs.

        If we give this function 0, what should it do? Well, simply just return currentNode. It's the first item.

        But what if it's more than 0? Say, 1? We need the next item. So we would call

        rest.get(0), since that will get the currentNode in rest. Simple. But.. what if we get 16? We can't really keep
        checking for numbers. But before freaking out, notice the pattern?

        You want the first element? Give the function 0, it gives you the first element.
        You want the second element? Give the function 1, we give 0 to the next function.
        You want the third element? Give the function 2, we give 1 to the next function, and that will give 0 to the one after that

        So, every time we recurse here, we want the index to either return the current node, or call the function
        on the next element, with one less index.
         */

        // Are you drunk?
        if(index < 0)
            return null;

        if(index == 0)
            return currentNode; // Found the index! Give the currentNode back.

        else if(rest == null)
            return null; // The index is greater than 0, and there's no more list. Give back null. Prevents NullPointerException

        else
            return rest.get(index - 1); // Keep going with a decremented index.

    }

    /**
     * Returns the length of the LinkedList. This will begin measuring at the first element. So:
     * [1] has a length of one. [1, [2]] has a length of two. etc.
     * @return The length of the LinkedList.
     */
    public int length(){

        if(rest == null) // Last element of the list! Count this element and stop recursing
            return 1;
        else

            //  CurrentElement  Length of rest
            //     V                 V
            return 1 +         rest.length();
    }

    /**
     * This function will generate a LinkedList from any number of items.
     *
     * This is something that Java calls a multivariate function. It allows you to pass in an arbitrary number
     * of arguments to the method. So,
     * generate(1) is just as passable of a syntax as generate(1, 2, 3, 4, 5, 6, 7, 8) is.
     *
     * @param <T> The type parameter
     * @return The LinkedList with these items added onto it.
     */
    public static <T> LinkedList<T> generate(T...items){

        LinkedList<T> localList = new LinkedList<T>();

        for(T item : items)
            localList.append(item);

        return localList;
    }


    /**
     * Prints out the LinkedList like it were stack frames. Even toString must be recursive.
     * @return The string representing this LinkedList
     */
    public String toString()
    {
        if(rest == null)
            return "[" + currentNode.toString() + "]";
        else
            return "[" + currentNode.toString() + ", " + rest.toString() + "]";
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*
    The basic functions of the LinkedList have been written. However, we can start to do some fancier stuff with it, just
    to see how deep some of this stuff really goes. For fun, these functions take a few essential java.util.function
    interfaces, and implement a stream-like API for them.
     */

    /**
     * Provides a copy of this ArrayList. This will be used when we want to perform an action that returns nothing on the
     * list, and then return the result of that action.
     * @return An exact copy of the list
     */
    public LinkedList<T> clone()
    {
        LinkedList<T> copy = new LinkedList<T>();

        for(int i = 0; i < this.length(); i++)
            copy.append(this.get(i));

        return copy;
    }

    /**
     * Function is a generic interface from java.util.function. It's one of the functions that lambdas can create.
     * It's used for lambdas which take something, do something to it, and then return it. For instance (x -> x + 1)
     * is (presumably) a Function<Integer, Integer>, since it looks like it's taking an integer, and adding an integer to
     * it, returning the resultant integer.
     *
     * This will take a function, create a new LinkedList by applying the function to the elements of this one, and return
     * it. The reason this doesn't use recursion to do this in-place is because we wouldn't be able to cascade in that case.
     *
     *
     * @param function The function that we'll be using on the list
     * @param <R> The return type of the function. It's usually the type of the LinkedList, but not always.
     * @return The new LinkedList
     */
    public <R> LinkedList<R> map(Function<T, R> function)
    {
        LinkedList<R> newList = new LinkedList<R>();

        for(int i = 0; i < this.length(); i++)
            newList.append(
                    function.apply(this.get(i))
            );

        return newList;
    }

    /**
     * Returns only the elements of the LinkedList for which the predicate is true
     * @param predicate The predicate to filter with
     * @return The filtered list
     */
    public LinkedList<T> filter(Predicate<T> predicate)
    {
        LinkedList<T> newList = new LinkedList<T>();

        for(int i = 0; i < this.length(); i++)
            if(predicate.test(this.get(i)))
                newList.append(this.get(i));

        return newList;
    }

    /**
     * BiFunction is the hardest of the functions to understand in java.util.function. Namely, because it takes three
     * different types. It takes <T, U, R>. T is the type of the first argument, U is the type of the second argument,
     * and R is the type that the function returns. However, since this is going to be reducing a list where all the types
     * are T, we're going to be working with <T, R, R>. This is because it will:
     *
     * * Get an element from the list, and the initial value
     * * Return something of type R
     * * Take that back it, and another T
     *
     * ... But Java doesn't handle <T, R, R> that great, because their type inference is worse than, say, Haskell's.
     * So we're stuck with BiFunction<T, T, T>
     * @param bifunction The binfunction that we'll be using
     * @param initialValue The initial value, of the type that the function returns.
     * @return The result after reducing the list
     */
    public T reduce(BiFunction<T, T, T> bifunction, T initialValue)
    {
        // Set up the initial value of the value
        T value = bifunction.apply(currentNode, initialValue);

        // We have to start at one, since this.get(0) would just get the currentNode
        for(int i = 1; i < this.length(); i++)
            value = bifunction.apply(this.get(i), value);

        return value;
    }
}
