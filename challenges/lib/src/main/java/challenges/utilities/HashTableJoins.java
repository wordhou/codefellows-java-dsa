package challenges.utilities;

import java.util.*;

public class HashTableJoins {
    static public <T, A, B> Map<T, Pair<A,B>> leftJoin(Map<T, A> map1, Map<T,B> map2) {
        Map<T, Pair<A,B>> result = new HashMap<>();

        map1.forEach((k, va) -> {
            B vb = map2.get(k);
            if (vb != null) result.put(k, new Pair<>(va, vb));
        });

        return result;
    }

//    static public <A, B, T> Map<T, Set<Pair<A,B>>> rightJoin(Map<A, T> map1, Map<B, T> map2) {
//        Map<T, Set<Pair<A,B>>> result = new HashMap<>();
//        Map<T, Set<B>> map2Rev = reverse(map2);
//
//        map1.forEach((k, v) -> {
//            Set<B> sb = map2Rev.get(v);
//            if (sb != null) {
//                Set<Pair<A,B>> setPairsAB = result.get(v);
//                if (setPairsAB == null) {
//                    setPairsAB = new HashSet<>();
//                    result.put(v, setPairsAB);
//                }
//                Set<Pair<A, B>> finalSetPairsAB = setPairsAB;
//                sb.forEach(kb -> finalSetPairsAB.add(new Pair(k, kb)));
//            }
//        });
//
//        return result;
//    }
//
//    static private <A, B> Map<B, Set<A>> reverse(Map<A, B> map) {
//        Map<B, Set<A>> result = new HashMap<>();
//
//        map.forEach((k, v) -> {
//            Set<A> l = result.get(v);
//            if (l == null) {
//                l = new HashSet<>();
//                result.put(v, l);
//            }
//            l.add(k);
//        });
//
//        return result;
//    }
//

    static class Pair<A, B> {
        A fst;
        B snd;

        public Pair(A fst, B snd) {
            this.fst = fst;
            this.snd = snd;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "fst=" + fst +
                    ", snd=" + snd +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return fst.equals(pair.fst) && snd.equals(pair.snd);
        }

        @Override
        public int hashCode() {
            return Objects.hash(fst, snd);
        }
    }

}

