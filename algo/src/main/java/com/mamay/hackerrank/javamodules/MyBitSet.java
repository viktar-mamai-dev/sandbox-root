package com.mamay.hackerrank.javamodules;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

interface PerformOperation {
  boolean check(int a);
}

class Prime {

  HashMap<Integer, Boolean> map = new HashMap<>();

  public void checkPrime(int... val) {
    for (int n1 : val) {
      Boolean b = map.get(n1);
      if (b != null) {
        if (b) System.out.print(n1 + " ");
      } else {
        boolean isPrime = isPrime(n1);
        map.put(n1, isPrime);
        if (isPrime) {
          System.out.print(n1 + " ");
        }
      }
    }
    System.out.println();
  }

  private boolean isPrime(int val) {
    if (val == 1) return false;
    if (val == 2 || val == 3) return true;
    if (val % 2 == 0) return false;
    for (int k = 3; k <= Math.sqrt(val); k += 2) {
      if (val % k == 0) {
        return false;
      }
    }
    return true;
  }
}

class MyMath {
  public boolean checker(PerformOperation p, int num) {
    return p.check(num);
  }

  public PerformOperation isOdd() {
    return new PerformOperation() {
      @Override
      public boolean check(int integer) {
        return integer % 2 == 1;
      }
    };
  }

  public PerformOperation isPrime() {
    return val -> {
      if (val < 2) return false;
      if (val == 2) return true;
      if (val % 2 == 0) return false;
      for (int k = 3; k <= Math.sqrt(val); k += 2) {
        if (val % k == 0) {
          return false;
        }
      }
      return true;
    };
  }

  public PerformOperation isPalindrome() {
    return integer -> {
      String str = String.valueOf(integer);
      StringBuilder b = new StringBuilder(str);

      return str.equals(b.reverse().toString());
    };
  }
}

public class MyBitSet {
  public static void main(String[] args) throws IOException {
    MyMath mymath = new MyMath();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    PerformOperation operation;
    boolean returnFlag = false;
    String ans = null;
    while (T-- > 0) {
      String s = br.readLine().trim();
      StringTokenizer st = new StringTokenizer(s);
      int ch = Integer.parseInt(st.nextToken());
      int num = Integer.parseInt(st.nextToken());
      if (ch == 1) {
        operation = mymath.isOdd();
        returnFlag = mymath.checker(operation, num);
        ans = (returnFlag) ? "ODD" : "EVEN";
      } else if (ch == 2) {
        operation = mymath.isPrime();
        returnFlag = mymath.checker(operation, num);
        ans = (returnFlag) ? "PRIME" : "COMPOSITE";
      } else if (ch == 3) {
        operation = mymath.isPalindrome();
        returnFlag = mymath.checker(operation, num);
        ans = (returnFlag) ? "PALINDROME" : "NOT PALINDROME";
      }
      System.out.println(ans);
    }
  }
}
