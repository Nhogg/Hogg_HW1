
/*
 * *** Nathan Hogg - Section 002 ***
 *
 * Homework # 1 (Programming Assignment). This Java class defines some basic
 * manipulation operations on Linked-Lists and Stacks.
 *
 * Additionally, there are two algorithm analysis methods where you need
 * to return a specified number as provided in comments of each method indicating
 * the complexity of the code shown. The testing routine will be looking for a
 * specific number returned.
 */

import java.util.Stack;

public class HW1 {

    /*
     * Class LinkedList
     *
     * This class builds a singly linked list. Each node of the linked-list
     * contains a single integer values.
     *
     * Methods:
     *  - void   sortInserted(val)     - Inserts value 'val' into the linked-list in
     *                                   sorted fashion
     *  - void   removeElementsLT(val) - Removed values in the linked-list that are less
     *                                   than 'val'
     *  - void   removeElement(val)    - Removes all values in the linked list of
     *                                   value 'val'
     *  - String toString()            - coverts and returns the linked-lists as a string
     *                                   delimited by brackets []
     *
     */

    static class LinkedList {
        static class Node {
            int data;
            Node next;

            Node(int d)  {        // Constructor
                data = d;
                next = null;
            }
        }
        Node head;                // head of Linked-list


        /*
         * Method sortedInsert() - this method will insert a new node to the
         * linked list containing the value specific in teh parameter 'data'.
         * The newly inserted node will be inserted in sorted order within
         * the linked-list.
         *
         */
        public void sortedInsert ( int data ) {
            Node new_node = new Node(data);

            new_node.next = null;

            // Special case for head node.
            if (this.head == null || head.data >= new_node.data ) {
                new_node.next = head;
                head = new_node;
            } else {
                // locate the node before the point of insertion
                Node current = this.head;

                // Identify where to place the item to insert
                while (current.next != null && current.next.data < data) {
                    current = current.next;
                }
                new_node.next = current.next;
                current.next = new_node;
            }

            return;
        }


        /**
         * Method removeElementsLT() - this method removes all nodes that contain a
         * value that is less than the provided parameter 'ltValue'.
         * This method traverses the linked list and calls method removeElement()
         * each time an element that is less than the ltValue parameter is met.
         *
         * @param ltValue int: value to check against linked list
         **/
        public void removeElementsLT ( int ltValue ) {
            Node current = this.head;

            // Traverse list until end is reached
            while (current != null) {
                // Call removeElement() if current value is less than ltValue
                // Since removeElement() handles the case of the head being removed,
                // we don't need to worry about that here.
                if (current.data < ltValue) {
                    removeElement(current.data);
                    // Shift cursor up one element.
                    current = current.next;
                } else {
                    // Base case for if element doesn't need to be removed.
                    current = current.next;
                }
            }
        }


        /**
         * Method removeElement() - this method removes all nodes that contain a
         * value equal to the value the provided parameter 'value'.
         * This method traverses the linked list and removes each instance
         * of the value passed via the parameter.
         *
         * @param value int: value to remove
         **/

        public void removeElement ( int value ) {
            Node current = this.head;
            // Since removing an element impacts the next and previous element, we must
            // keep track of each previous element.
            Node prev = null;

            // Traverse the linked list until the end is reached
            while (current != null) {
                // Check if current value is equal to the one to be removed
                if (current.data == value) {
                    // Special case for removing the head element
                    if (prev == null) {
                        this.head = current.next;
                    } else {
                        // Shift previous cursor up one
                        prev.next = current.next;
                    }
                    // shift cursor up by one
                    current = current.next;
                } else {
                    // base case if element is not found
                    prev = current;
                    current = current.next;
                }
            }
        }


        /*
         * Method toString() - this is a helper method for printing / constructing
         * a string object from the linked-list.
         */
        public String toString () // Method to output the LinkedList as a String
        {
            String output = "[";
            Node currNode = this.head;
            while (currNode != null) {
                output += currNode.data + " ";
                currNode = currNode.next;
            }
            return output.trim() + "]";
        }

    } // End class LinkedList




    /*
     * Class Stacks
     *
     * This class utilizes the Java Common Framework Library Stack class.
     *
     * The intent of this class is to write methods which utilize the Java
     * library's Stack class. You need to utilize this library class in
     * your solution.
     *
     * Methods:
     *  - boolean isPalindrome(string)   - method returns true or false if 'string'
     *                                     is a palindrome
     *  - int     findlargestK(stack, k) - method accepts a stack and returns the
     *                                     the largest index in the stack containing
     *                                     value 'k' (stack index starts at 0)
     *
     */

    static class Stacks {

        /**
         * Method isPalindrome() - This method will return the boolean value 'true'
         * or 'false' on if the passed in parameter string is a palindrome or not.
         *
         * The routine should be case insensitive! Meaning 'RaCe cAr' is a palindrome.
         * Moreover, spaces are ignore, so both 'race car' and 'racecar' are plaindromes.
         *
         * The method should utilize the provided Stack class.
         *
         * @param input String: String to run Palindrome test on
         **/
        public static boolean isPalindrome(String input) {
            // Declare return value
            boolean isPalindrome = true;
            // Initialize new stack for input
            Stack<Character> stack = new Stack<>();
            // Strip case of input
            input = input.toLowerCase().replaceAll("\\s+", "");

            // Determine length of input String
            int length = input.length();
            // Calculate the middle index of input
            int middle = length / 2, start = length / 2;

            // Push input to stack
            for (int i = 0; i < middle; i++) {
                stack.push(input.charAt(i));
            }
            // Since odd-length strings have a letter right in the middle,
            // we can discard that letter as it is the same for both halves.
            if (length % 2 != 0) {
                start++;
            }

            // Loop runs while we are not at the end of the String
            while (start < length) {
                char currentElement = stack.pop();
                // Check if the characters are different. If they are,
                // the String is not a palindrome.
                if (currentElement != input.charAt(start))
                    isPalindrome = false;
                start++;
            }

            return isPalindrome;
        }


        /**
         * Method findLargestk() - This method will return the largest index
         * position in the stack for the value specified by the parameter 'k'.
         *
         * Note that the bottom of the stack is index location 0. So it you push
         * on to the stack the values 3 4 9 4 4 7 4, in that order. And you pass the
         * value '4' for the parameter k, then the largest index position is index
         * location 6.
         * Method uses the stack.get() function and a for loop to check each element
         * in the stack and determine if it equals k. If it does, the largest variable
         * is set to that index, until the top of the stack is reached.
         *
         * @param stack Stack: the stack to analyze
         * @param k int: the integer we are searching for
         **/
        public static int findLargestK(Stack<Integer> stack, int k) {
            // Since the index of a stack starts at 0, we need to initialize our counter
            // to -1. This is because, if the stack didn't contain the value, it would
            // default to the zeroth position. This is incorrect.
            int largest = -1;
            // iterate over each element in the stack.
            for (int i = 0; i < stack.size(); i++) {
                // Check if the current element is equal to k
                if (stack.get(i) == k) {
                    largest = i;
                }
            }
            return largest;
        }

    }  // End class Stacks



    /*******************************
     *
     * Algorithm Analysis
     *
     * Below are two methods, algorithmAnalysis1 and algorithmAnalysis2.
     * Modify the return statement to return the correct option number.
     *
     *********************************/

    public static int algorithmAnalysis1(int n, int m) {
        int a = 0, b = 0;

        for (int i=0; i < n; i++)
            a+= Math.random();

        for (int j=0; j < m; j++)
            b+= Math.random();

        /*
         * Select the correct option listed below:
         *   1. O(N * M) time, O(1) space
         *   2. O(N + M) time, O(N + M) space
         *   3. O(N + M) time, O(1) space
         *   4. O(N * M) time, O(N + M) space
         *
         * TODO: return the answer (which option is correct), in the return statement
        */

        // RETURN THE CORRECT OPTION NUMBER LISTED ABOVE
        return 3;
    }


    public static int algorithmAnalysis2(int n) {
        int i, j, k = 0;
        for (i = n/2; i <= n; i++)
            for ( j = 2; j <= n; j = j*2 )
                k+= n/2;

        /*
         * Select the correct option listed below:
         *   1. O(N) time
         *   2. O(N log N) time
         *   3. O(N^2) time
         *   4. O(N^2Log n) time
         *
         * TODO: return the answer (which option is correct), in the return statement
         */

        // RETURN THE CORRECT OPTION LISTED ABOVE
        return 2;
    }

}

