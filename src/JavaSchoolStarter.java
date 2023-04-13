import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class JavaSchoolStarter {
    public List<Map<String, Object>> array = new ArrayList<>();

    public List<Map<String, Object>> execute(String string) throws Exception {
        switch (string.substring(0, 6).toLowerCase()) {
            case "select":
                return parseSelect(string);
            case "insert":
                array.add(parseInsert(string));
                return array;
            case "delete":
                return parseDelete(string);
            case "update":
                return parseUpdate(string);
            default:
                throw new Exception();
        }

    }

    public JavaSchoolStarter() {
    }


    public static Map<String, Object> parseInsert(String string) throws Exception {
        string = string.substring(JavaSchoolStarter.indexOf(string.toLowerCase(), "VALUES|values") + 6);
        Map<String, Object> map = new HashMap<>();
        if (string.contains(",")) {
            List<String> list = List.of(string.split(","));
            for (int i = 0; i < list.size(); i++) {
                List<String> split = List.of(list.get(i).trim().split("="));
                switch (split.get(0).trim().substring(1, split.get(0).trim().length() - 1).toLowerCase()) {
                    case "lastname":
                        if (Objects.equals(split.get(1).trim().substring(1, split.get(1).trim().length() - 1), "null") == false) {
                            map.put("lastname", split.get(1).trim().substring(1, split.get(1).trim().length() - 1));
                        } else {
                            map.put("lastname", null);
                        }
                        break;
                    case "active":
                        if (Objects.equals(split.get(1).trim(), "null") == false) {
                            map.put("active", Boolean.valueOf(split.get(1).trim()));
                        } else {
                            map.put("active", null);
                        }
                        break;
                    case "age":
                        if (Objects.equals(split.get(1).trim(), "null") == false) {

                            map.put("age", Long.valueOf(split.get(1).trim()));
                        } else {
                            map.put("age", null);
                        }
                        break;
                    case "cost":
                        if (Objects.equals(split.get(1).trim(), "null") == false) {
                            map.put("cost", Double.valueOf(split.get(1).trim()));
                        } else {
                            map.put("cost", null);
                        }
                        break;
                    case "id":
                        if (Objects.equals(split.get(1).trim(), "null") == false) {
                            map.put("id", Long.valueOf(split.get(1).trim()));
                        } else {
                            map.put("id", null);
                        }
                        break;
                    default:
                        throw new Exception();
                }

            }
        } else
        {

            List<String> split = List.of(string.trim().split("="));
            switch (split.get(0).trim().substring(1, split.get(0).trim().length() - 1).toLowerCase()) {
                case "lastname":
                    if (Objects.equals(split.get(1).trim().substring(1, split.get(1).trim().length() - 1), "null") == false) {
                        map.put("lastname", split.get(1).trim().substring(1, split.get(1).trim().length() - 1));
                    } else {
                        map.put("lastname", null);
                    }

                    break;
                case "active":
                    if (Objects.equals(split.get(1).trim(), "null") == false) {
                        map.put("active", Boolean.valueOf(split.get(1).trim()));
                    } else {
                        map.put("active", null);
                    }

                    break;
                case "age":
                    if (Objects.equals(split.get(1).trim(), "null") == false) {

                        map.put("age", Long.valueOf(split.get(1).trim()));
                    } else {
                        map.put("age", null);
                    }

                    break;
                case "cost":
                    if (Objects.equals(split.get(1).trim(), "null") == false) {
                        map.put("cost", Double.valueOf(split.get(1).trim()));
                    } else {
                        map.put("cost", null);
                    }

                    break;
                case "id":
                    if (Objects.equals(split.get(1).trim(), "null") == false) {
                        map.put("id", Long.valueOf(split.get(1).trim()));
                    } else {
                        map.put("id", null);
                    }
                    break;
                default:
                    throw new Exception();
            }
        }
        return map;
    }

    public static int indexOf(String s, String pattern) {
        Matcher matcher = Pattern.compile(pattern).matcher(s);
        if (matcher.find()) {
            return matcher.start();
        } else {
            return -1;
        }
    }

    public static Predicate parseTerm(String string) throws Exception {

        Predicate predicate = null;
        List<String> list;
        if (string.contains("!=")) {

            list = List.of(string.split("!="));
            switch (list.get(0).trim().substring(1, list.get(0).trim().length() - 1).toLowerCase()) {
                case "lastname":
                    predicate = (map) -> !map.get(list.get(0).trim().substring(1, list.get(0).trim().length() - 1)
                            .toLowerCase()).equals(list.get(1).trim().substring(1, list.get(1).trim().length() - 1));
                    break;
                case "active":
                    predicate = (map) -> (Boolean) map.get(list.get(0).trim().substring(1, list.get(0).trim().length() - 1)
                            .toLowerCase()) != Boolean.valueOf(list.get(1).trim());
                    break;
                case "age":
                    predicate = (map) -> (Long) map.get(list.get(0).trim().substring(1, list.get(0).trim().length() - 1)
                            .toLowerCase()) != Long.valueOf(list.get(1).trim());
                    break;
                case "cost":
                    predicate = (map) -> Double.valueOf((Double) map.get(list.get(0).trim().substring(1, list.get(0).trim()
                            .length() - 1).toLowerCase())).compareTo(Double.valueOf(list.get(1).trim())) != 0;
                    break;
                case "id":
                    predicate = (map) -> (Long) map.get(list.get(0).trim().substring(1, list.get(0).trim().length() - 1)
                            .toLowerCase()) != Long.valueOf(list.get(1).trim());
                    break;
                default:
                    throw new Exception();
            }


        } else {
            if (string.contains(">=")) {

                list = List.of(string.split(">="));
                if (list.get(0).trim().substring(1, list.get(0).trim().length() - 1).toLowerCase().equals("age")) {
                    predicate = (map) -> (Long) map.get(list.get(0).trim().substring(1, list.get(0).trim().length() - 1).toLowerCase()) >=
                            Long.valueOf(list.get(1).trim());
                } else {
                    if (list.get(0).trim().substring(1, list.get(0).trim().length() - 1).toLowerCase().equals("cost")) {
                        predicate = (map) -> (Double) map.get(list.get(0).trim().substring(1, list.get(0).trim().length() - 1).toLowerCase()) >
                                Double.valueOf(list.get(1).trim())
                                || Double.valueOf((Double) map.get(list.get(0).trim()
                                        .substring(1, list.get(0).trim().length() - 1).toLowerCase()))
                                .compareTo(Double.valueOf(list.get(1).trim())) == 0;
                    } else {
                        throw new Exception();
                    }

                }

            } else {
                if (string.contains("<=")) {

                    list = List.of(string.split("<="));
                    if (list.get(0).trim().substring(1, list.get(0).trim().length() - 1).toLowerCase().equals("age")) {
                        predicate = (map) -> (Long) map.get(list.get(0).trim().substring(1, list.get(0).trim().length() - 1).toLowerCase()) <=
                                Long.valueOf(list.get(1).trim());
                    } else {
                        if (list.get(0).trim().substring(1, list.get(0).trim().length() - 1).toLowerCase().equals("cost")) {
                            predicate = (map) -> (Double) map.get(list.get(0).trim().substring(1, list.get(0).trim().length() - 1).toLowerCase()) <
                                    Double.valueOf(list.get(1).trim())
                                    || Double.valueOf((Double) map.get(list.get(0).trim()
                                            .substring(1, list.get(0).trim().length() - 1).toLowerCase()))
                                    .compareTo(Double.valueOf(list.get(1).trim())) == 0;
                        } else {
                            throw new Exception();
                        }

                    }

                } else {
                    if (string.contains("<")) {

                        list = List.of(string.split("<"));
                        if (list.get(0).trim().substring(1, list.get(0).trim().length() - 1).toLowerCase().equals("age")) {
                            predicate = (map) -> (Long) map.get(list.get(0).trim().substring(1, list.get(0).trim().length() - 1).toLowerCase()) <
                                    Long.valueOf(list.get(1).trim());
                        } else {
                            if (list.get(0).trim().substring(1, list.get(0).trim().length() - 1).toLowerCase().equals("cost")) {
                                predicate = (map) -> (Double) map.get(list.get(0).trim().substring(1, list.get(0).trim().length() - 1).toLowerCase()) <
                                        Double.valueOf(list.get(1).trim());
                            } else {
                                throw new Exception();
                            }
                        }
                    } else {
                        if (string.contains(">")) {

                            list = List.of(string.split(">"));
                            if (list.get(0).trim().substring(1, list.get(0).trim().length() - 1).toLowerCase().equals("age")) {
                                predicate = (map) -> (Long) map.get(list.get(0).trim().substring(1, list.get(0).trim().length() - 1).toLowerCase()) >
                                        Long.valueOf(list.get(1).trim());
                            } else {
                                if (list.get(0).trim().substring(1, list.get(0).trim().length() - 1).toLowerCase().equals("cost")) {
                                    predicate = (map) -> (Double) map.get(list.get(0).trim().substring(1, list.get(0).trim().length() - 1).toLowerCase()) >
                                            Double.valueOf(list.get(1).trim());
                                } else {
                                    throw new Exception();
                                }
                            }

                        } else {
                            if (string.contains("=")) {

                                list = List.of(string.split("="));
                                switch (list.get(0).trim().substring(1, list.get(0).trim().length() - 1).toLowerCase()) {
                                    case "lastname":
                                        predicate = (map) -> map.get(list.get(0).trim().substring(1, list.get(0).trim().length() - 1).toLowerCase())
                                                .equals(list.get(1).trim().substring(1, list.get(1).trim().length() - 1));
                                        break;
                                    case "active":
                                        predicate = (map) -> (Boolean) map.get(list.get(0).trim().substring(1, list.get(0).trim().length() - 1).toLowerCase()) ==
                                                Boolean.valueOf(list.get(1).trim());
                                        break;
                                    case "age":
                                        predicate = (map) -> (Long) map.get(list.get(0).trim().substring(1, list.get(0).trim().length() - 1).toLowerCase()) ==
                                                Long.valueOf(list.get(1).trim());
                                        break;
                                    case "cost":
                                        predicate = (map) -> Double.valueOf((Double) map.get(list.get(0).trim().substring(1, list.get(0).trim().length() - 1).toLowerCase()))
                                                .compareTo(Double.valueOf(list.get(1).trim())) == 0;
                                        break;
                                    case "id":
                                        predicate = (map) -> (Long) map.get(list.get(0).trim().substring(1, list.get(0).trim().length() - 1).toLowerCase()) ==
                                                Long.valueOf(list.get(1).trim());
                                        break;
                                    default:
                                        throw new Exception();

                                }

                            } else {

                                if (string.contains("ilike")) {


                                    list = List.of(string.split("ilike"));
                                    if (list.get(0).trim().substring(1, list.get(0).trim().length() - 1).toLowerCase().equals("lastname")) {

                                        if (list.get(1).trim().charAt(1) == '%' && list.get(1).trim().charAt(list.get(1).trim().length() - 2) == '%') {

                                            predicate = (map) -> map.get(list.get(0).trim().substring(1, list.get(0).trim().length() - 1).toLowerCase())
                                                    .toString().toLowerCase().contains(list.get(1).trim().substring(2, list.get(1).trim().length() - 2).toLowerCase());
                                        } else {
                                            if (list.get(1).trim().charAt(1) == '%') {

                                                predicate = (map) -> map.get(list.get(0).trim().substring(1, list.get(0).trim().length() - 1).toLowerCase())
                                                        .toString().substring(0, list.get(1).trim().length() - 3)
                                                        .equalsIgnoreCase(list.get(1).trim().substring(2, list.get(1).trim().length() - 1));


                                            } else {
                                                if (list.get(1).trim().charAt(list.get(1).trim().length() - 2) == '%') {
                                                    predicate = map -> map.get(list.get(0).trim().substring(1, list.get(0).trim().length() - 1).toLowerCase()).toString()
                                                            .substring(map.get(list.get(0).trim().substring(1, list.get(0).trim().length() - 1).toLowerCase()).toString().length() - list.get(1).trim().substring(1, list.get(1).trim().length() - 2).length(), map.get(list.get(0).trim().substring(1, list.get(0).trim().length() - 1).toLowerCase()).toString().length())
                                                            .equalsIgnoreCase(list.get(1).trim().substring(1, list.get(1).trim().length() - 2));
                                                } else {
                                                    if (list.get(1).trim().contains("%") == false) {

                                                        predicate = map -> map.get(list.get(0).trim().substring(1, list.get(0)
                                                                        .trim().length() - 1).toLowerCase()).toString()
                                                                .equalsIgnoreCase(list.get(1).trim().substring(1, list.get(1).trim().length() - 1));
                                                    } else {
                                                        throw new Exception();
                                                    }
                                                }

                                            }
                                        }

                                    } else {
                                        throw new Exception();
                                    }


                                } else {
                                    if (string.contains("like")) {
                                        list = List.of(string.split("like"));
                                        if (list.get(0).trim().substring(1, list.get(0).trim().length() - 1).toLowerCase().equals("lastname")) {

                                            if (list.get(1).trim().charAt(1) == '%' && list.get(1).trim().charAt(list.get(1).trim().length() - 2) == '%') {

                                                predicate = (map) -> map.get(list.get(0).trim().substring(1, list.get(0).trim().length() - 1).toLowerCase())
                                                        .toString().contains(list.get(1).trim().substring(2, list.get(1).trim().length() - 2));
                                            } else {
                                                if (list.get(1).trim().charAt(1) == '%') {

                                                    predicate = (map) -> map.get(list.get(0).trim().substring(1, list.get(0).trim().length() - 1).toLowerCase())
                                                            .toString().substring(0, list.get(1).trim().length() - 3)
                                                            .equals(list.get(1).trim().substring(2, list.get(1).trim().length() - 1));


                                                } else {
                                                    if (list.get(1).trim().charAt(list.get(1).trim().length() - 2) == '%') {

                                                        predicate = map -> map.get(list.get(0).trim().substring(1, list.get(0).trim().length() - 1).toLowerCase()).toString()
                                                                .substring(map.get(list.get(0).trim().substring(1, list.get(0).trim().length() - 1).toLowerCase()).toString().length() - list.get(1).trim().substring(1, list.get(1).trim().length() - 2).length(), map.get(list.get(0).trim().substring(1, list.get(0).trim().length() - 1).toLowerCase()).toString().length())
                                                                .equals(list.get(1).trim().substring(1, list.get(1).trim().length() - 2));
                                                    } else {
                                                        if (list.get(1).trim().contains("%") == false) {

                                                            predicate = map -> map.get(list.get(0).trim().substring(1, list.get(0)
                                                                            .trim().length() - 1).toLowerCase()).toString()
                                                                    .equals(list.get(1).trim().substring(1, list.get(1).trim().length() - 1));
                                                        } else {
                                                            throw new Exception();
                                                        }
                                                    }

                                                }
                                            }

                                        } else {
                                            throw new Exception();
                                        }


                                    }
                                }


                            }
                        }

                    }
                }
            }

        }
        return predicate;
    }

    public static Predicate mergePredicate(Predicate p1, Predicate p2) {
        return (el) -> p1.validate(el) && p2.validate(el);
    }

    public static Predicate finalMergePredicate(Predicate p1, Predicate p2) {
        return (el) -> p1.validate(el) || p2.validate(el);
    }

    public void printList() {
        array.stream().forEach((p) -> System.out.println(p));
    }

    public List<Map<String, Object>> parseUpdate(String string) throws Exception {

        if (string.toLowerCase().contains("where")) {
            Map<String, Object> condition = JavaSchoolStarter.parseInsert(string.substring(JavaSchoolStarter
                    .indexOf(string.toLowerCase(), "VALUES|values"), JavaSchoolStarter.indexOf(string.toLowerCase(), "WHERE|where")));


            string = string.substring(JavaSchoolStarter.indexOf(string.toLowerCase(), "WHERE|where") + 5).trim();
            List<Predicate> predicates = new ArrayList<>();
            Predicate predicate;
            if (JavaSchoolStarter.indexOf(string.toLowerCase(), "and|or|AND|OR") != -1) {
                predicate = JavaSchoolStarter.parseTerm(string.substring(0, JavaSchoolStarter.indexOf(string.toLowerCase(), "and|or|AND|OR")));
                while (string != null) {

                    if (JavaSchoolStarter.indexOf(string.toLowerCase(), "and|or|AND|OR") != -1) {
                        string = string.substring(JavaSchoolStarter.indexOf(string.toLowerCase(), "and|or|AND|OR"));
                        if (string.toLowerCase().substring(0, 3).equals("and")) {
                            string = string.substring(3, string.length());

                            if (JavaSchoolStarter.indexOf(string.toLowerCase(), "and|or|AND|OR") != -1) {
                                predicate = JavaSchoolStarter.mergePredicate(predicate, JavaSchoolStarter.parseTerm(string.substring(0, JavaSchoolStarter.indexOf(string.toLowerCase(), "and|or|AND|OR"))));
                            } else {
                                predicate = JavaSchoolStarter.mergePredicate(predicate, JavaSchoolStarter.parseTerm(string));
                            }
                        }
                        if (string.toLowerCase().substring(0, 2).equals("or")) {
                            string = string.substring(2, string.length());

                            predicates.add(predicate);
                            if (JavaSchoolStarter.indexOf(string.toLowerCase(), "and|or|AND|OR") == -1) {
                                predicate = JavaSchoolStarter.parseTerm(string);
                            } else {
                                predicate = JavaSchoolStarter.parseTerm(string.substring(0, JavaSchoolStarter.indexOf(string.toLowerCase(), "and|or|AND|OR")));
                            }
                        }
                    } else {
                        predicates.add(predicate);
                        predicate = predicates.get(0);
                        for (int i = 1; i < predicates.size(); i++) {
                            predicate = JavaSchoolStarter.finalMergePredicate(predicate, predicates.get(i));
                        }

                        for (int i = 0; i < array.size(); i++) {
                            if (predicate.validate(array.get(i))) {
                                array.set(i, replaceMap(condition, array.get(i)));
                            }
                        }
                        return array;

                        //array.stream().filter(el-> finalPredicate1.validate(el)).collect(Collectors.toList());
                    }
                }

            } else {

                predicates.add(JavaSchoolStarter.parseTerm(string));
                for (int i = 0; i < array.size(); i++) {
                    if (predicates.get(0).validate(array.get(i))) {
                        array.set(i, replaceMap(condition, array.get(i)));
                    }
                }
                return array;
                //array.stream().filter(el->predicates.get(0).validate(el)).collect(Collectors.toList());
            }


        } else {
            Map<String, Object> condition = JavaSchoolStarter.parseInsert(string.substring(JavaSchoolStarter
                    .indexOf(string.toLowerCase(), "VALUES|values")));
            for (int i = 0; i < array.size(); i++) {
                array.set(i, replaceMap(condition, array.get(i)));
            }
            return array;
        }
        return null;
    }

    public List<Map<String, Object>> parseSelect(String string) throws Exception {
        List<Map<String, Object>> list = array;
        if (string.toLowerCase().contains("where")) {
            string = string.substring(JavaSchoolStarter.indexOf(string.toLowerCase(), "WHERE|where") + 5).trim();
            List<Predicate> predicates = new ArrayList<>();
            Predicate predicate;
            if (JavaSchoolStarter.indexOf(string.toLowerCase(), "and|or|AND|OR") != -1) {
                predicate = JavaSchoolStarter.parseTerm(string.substring(0, JavaSchoolStarter.indexOf(string.toLowerCase(), "and|or|AND|OR")));
                while (string != null) {

                    if (JavaSchoolStarter.indexOf(string.toLowerCase(), "and|or|AND|OR") != -1) {
                        string = string.substring(JavaSchoolStarter.indexOf(string.toLowerCase(), "and|or|AND|OR"));
                        if (string.toLowerCase().substring(0, 3).equals("and")) {
                            string = string.substring(3, string.length());

                            if (JavaSchoolStarter.indexOf(string.toLowerCase(), "and|or|AND|OR") != -1) {
                                predicate = JavaSchoolStarter.mergePredicate(predicate, JavaSchoolStarter.parseTerm(string.substring(0, JavaSchoolStarter.indexOf(string.toLowerCase(), "and|or|AND|OR"))));
                            } else {
                                predicate = JavaSchoolStarter.mergePredicate(predicate, JavaSchoolStarter.parseTerm(string));
                            }
                        }
                        if (string.toLowerCase().substring(0, 2).equals("or")) {
                            string = string.substring(2, string.length());

                            predicates.add(predicate);
                            if (JavaSchoolStarter.indexOf(string.toLowerCase(), "and|or|AND|OR") == -1) {
                                predicate = JavaSchoolStarter.parseTerm(string);
                            } else {
                                predicate = JavaSchoolStarter.parseTerm(string.substring(0, JavaSchoolStarter.indexOf(string.toLowerCase(), "and|or|AND|OR")));
                            }
                        }
                    } else {
                        predicates.add(predicate);
                        predicate = predicates.get(0);
                        for (int i = 1; i < predicates.size(); i++) {
                            predicate = JavaSchoolStarter.finalMergePredicate(predicate, predicates.get(i));
                        }
                        Predicate finalPredicate1 = predicate;
                        return list.stream().filter(el -> finalPredicate1.validate(el)).collect(Collectors.toList());
                    }
                }

            } else {

                predicates.add(JavaSchoolStarter.parseTerm(string));
                return list.stream().filter(el -> predicates.get(0).validate(el)).collect(Collectors.toList());
            }
        } else {
            return list;
        }
        return null;
    }

    public List<Map<String, Object>> parseDelete(String string) throws Exception {
        if (string.toLowerCase().contains("where")) {
            string = string.substring(JavaSchoolStarter.indexOf(string.toLowerCase(), "WHERE|where") + 5).trim();
            List<Predicate> predicates = new ArrayList<>();
            Predicate predicate;
            if (JavaSchoolStarter.indexOf(string.toLowerCase(), "and|or|AND|OR") != -1) {
                predicate = JavaSchoolStarter.parseTerm(string.substring(0, JavaSchoolStarter.indexOf(string.toLowerCase(), "and|or|AND|OR")));
                while (string != null) {

                    if (JavaSchoolStarter.indexOf(string.toLowerCase(), "and|or|AND|OR") != -1) {
                        string = string.substring(JavaSchoolStarter.indexOf(string.toLowerCase(), "and|or|AND|OR"));
                        if (string.toLowerCase().substring(0, 3).equals("and")) {
                            string = string.substring(3, string.length());

                            if (JavaSchoolStarter.indexOf(string.toLowerCase(), "and|or|AND|OR") != -1) {
                                predicate = JavaSchoolStarter.mergePredicate(predicate, JavaSchoolStarter.parseTerm(string.substring(0, JavaSchoolStarter.indexOf(string.toLowerCase(), "and|or|AND|OR"))));
                            } else {
                                predicate = JavaSchoolStarter.mergePredicate(predicate, JavaSchoolStarter.parseTerm(string));
                            }
                        }
                        if (string.toLowerCase().substring(0, 2).equals("or")) {
                            string = string.substring(2, string.length());

                            predicates.add(predicate);
                            if (JavaSchoolStarter.indexOf(string.toLowerCase(), "and|or|AND|OR") == -1) {
                                predicate = JavaSchoolStarter.parseTerm(string);
                            } else {
                                predicate = JavaSchoolStarter.parseTerm(string.substring(0, JavaSchoolStarter.indexOf(string.toLowerCase(), "and|or|AND|OR")));
                            }
                        }
                    } else {
                        predicates.add(predicate);
                        predicate = predicates.get(0);
                        for (int i = 1; i < predicates.size(); i++) {
                            predicate = JavaSchoolStarter.finalMergePredicate(predicate, predicates.get(i));
                        }
                        for (int i = 0; i < array.size(); i++) {
                            if (predicate.validate(array.get(i))) {
                                array.remove(array.get(i));
                            }
                        }
                        return array;
                    }
                }

            } else {
                predicates.add(JavaSchoolStarter.parseTerm(string));
                for (int i = 0; i < array.size(); i++) {
                    if (predicates.get(0).validate(array.get(i))) {
                        array.remove(array.get(i));
                    }
                }
                return array;
            }
        } else {
            array.clear();
            return array;
        }
        return null;
    }

    public Map<String, Object> replaceMap(Map<String, Object> condition, Map<String, Object> replace) {
        if (condition.containsKey("lastname")) {
            replace.put("lastname", condition.get("lastname"));
        }
        if (condition.containsKey("age")) {
            replace.put("age", condition.get("age"));
        }
        if (condition.containsKey("cost")) {
            replace.put("cost", condition.get("cost"));
        }
        if (condition.containsKey("active")) {
            replace.put("active", condition.get("active"));
        }
        if (condition.containsKey("id")) {
            replace.put("id", condition.get("id"));
        }

        return replace;
    }


}
