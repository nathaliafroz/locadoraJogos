package edu.ifma.labd;

import edu.ifma.labd.util.JPAUtil;

public class Main {
    public static void main(String[] args) {
        JPAUtil.getEntityManager();
        JPAUtil.close();
        System.out.println("Tabelas criadas com sucesso!");
    }
}