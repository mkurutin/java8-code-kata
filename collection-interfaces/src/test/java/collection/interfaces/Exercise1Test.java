package collection.interfaces;

import common.test.tool.annotation.Necessity;
import common.test.tool.dataset.ClassicOnlineStore;
import common.test.tool.entity.Customer;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class Exercise1Test extends ClassicOnlineStore {

    @Test
    @Necessity(true)
    public void iterateByForEach() {
        Iterable<Customer> customerIterable = this.mall.getCustomerList();
        List<String> nameList = new ArrayList<>();

        /**
         * Create a {@link Consumer} which represents an operation to add customer's name to {@link nameList} list.
         * Iterate {@link customerIterable} with {@link Iterable#forEach} and use the {@link Consumer}
         * to finish creating the name list.
         */
        customerIterable.forEach(customer -> nameList.add(customer.getName()));

        assertThat(nameList.toString(), is("[Joe, Steven, Patrick, Diana, Chris, Kathy, Alice, Andrew, Martin, Amy]"));
    }

    @Test
    @Necessity(true)
    public void whoHaveNoEInYourName() {
        Collection<String> nameCollection =
            new ArrayList<>(Arrays.asList("Joe", "Steven", "Patrick", "Chris", "Elon"));

        /**
         * Create a {@link Predicate} which predicates whether the input string containing string "e".
         * Remove elements from {@link nameCollection} which
         */
        nameCollection.removeIf(name -> name.toLowerCase().contains("e"));

        assertThat(nameCollection.toString(), is("[Patrick, Chris]"));
    }

    @Test
    @Necessity(true)
    public void replaceTheElements() {
        List<String> nameList =
            new ArrayList<>(Arrays.asList("Joe", "Steven", "Patrick", "Chris"));

        /**
         * Create a {@link UnaryOperator} which returns given string wrapped with "()".
         * Replace the elements in {@link nameList} with string wrapped with brackets like shown in the assertion.
         */
        nameList.replaceAll(name -> format("(%s)", name));

        assertThat(nameList.toString(), is("[(Joe), (Steven), (Patrick), (Chris)]"));
    }

    @Test
    @Necessity(true)
    public void sortByName() {
        List<String> nameList = asList("Joe", "Steven", "Patrick", "Chris");

        /**
         * Create a {@link Comparator} to sort the name list by their name's length in ascending order.
         */
        nameList.sort((name1, name2) -> name1.length() - name2.length());

        assertThat(nameList.toString(), is("[Joe, Chris, Steven, Patrick]"));
    }

    @Test
    @Necessity(true)
    public void createStream() {
        Collection<String> nameList = asList("Joe", "Steven", "Patrick", "Chris");

        /**
         * Create a serial {@link Stream} using {@link Collection#stream}
         * You can learn about {@link Stream} APIs at stream-api module.
         */
        Stream<String> nameStream = nameList.stream();

        assertThat(nameStream.count(), is(4L));
        assertThat(nameStream.isParallel(), is(false));
    }

    @Test
    @Necessity(true)
    public void createParallelStream() {
        Collection<String> nameList = asList("Joe", "Steven", "Patrick", "Chris");

        /**
         * Create a serial {@link Stream} using {@link Collection#parallelStream} or {@link Stream#parallel}
         */
        Stream<String> nameParallelStream = nameList.parallelStream();

        assertThat(nameParallelStream.count(), is(4L));
        assertThat(nameParallelStream.isParallel(), is(true));
    }
}
