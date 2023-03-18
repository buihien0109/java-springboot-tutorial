package com.example.jpanew.createId;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Product {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @Table(name = "product_zero")
    static
    public class ProductZero {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        @Column(name = "id", nullable = false)
        private Integer id;

        private String name;

        public ProductZero(String name) {
            this.name = name;
        }
    }
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @Table(name = "product_one")
    static
    public class ProductOne {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_one_seq")
        @SequenceGenerator(name = "product_one_seq", allocationSize = 1)
        @Column(name = "id", nullable = false)
        private Integer id;

        private String name;

        public ProductOne(String name) {
            this.name = name;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @Table(name = "product_two")
    static
    public class ProductTwo {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id", nullable = false)
        private Integer id;

        private String name;

        public ProductTwo(String name) {
            this.name = name;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @Table(name = "product_three")
    static
    public class ProductThree {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        private Integer id;

        private String name;

        public ProductThree(String name) {
            this.name = name;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @Table(name = "product_four")
    static
    public class ProductFour {
        @Id
        @GeneratedValue(strategy = GenerationType.TABLE)
        @Column(name = "id", nullable = false)
        private Integer id;

        private String name;

        public ProductFour(String name) {
            this.name = name;
        }
    }
}
