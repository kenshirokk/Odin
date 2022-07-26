package com.ken.toolbox.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {

        List<People> list = new ArrayList<>();
        People p1 = new People();
        List<String> gf1 = List.of("1", "2", "3");
        p1.setGirlFriends(gf1);

        People p2 = new People();
        List<String> gf2 = List.of("a", "b", "c");
        p2.setGirlFriends(gf2);

        list.add(p1);
        list.add(p2);

        List<String> collect = list.stream().flatMap(g -> g.getGirlFriends().stream()).collect(Collectors.toList());

        collect.forEach(System.out::println);
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class People {
    List<String> girlFriends;
}
