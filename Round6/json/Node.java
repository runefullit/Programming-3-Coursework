
import java.util.ArrayList;

public abstract class Node {
  public boolean isValue() {
    return this instanceof ValueNode;
  }

  public boolean isArray() {
    return this instanceof ArrayNode;
  }

  public boolean isObject() {
    return this instanceof ObjectNode;
  }
  
  public void printSimple() {
    StringBuilder sb = new StringBuilder();
    printSimple(this, sb);
    System.out.print(sb.toString());
  }

  public void printJson() {
    System.out.print(printJson(this,""));
  }
  
/**
 * Recursively build printable JSON representation starting from the root node it is called with. The function treats
 * each level of recursion as a list that is joined together with appropriate padding.
 * @param node
 * @param indentation
 * @param sb
 * @return 
 */  
 private String printJson(Node node, String indentation){
    ArrayList<String> strs = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    if(node.isObject()) {
        ObjectNode objNode = (ObjectNode) node;
        if (objNode.size() > 0){
            // Indentation of the first line is taken care of by the segment calling it.
            sb.append("{").append(NL);
            for(String name : objNode) {
                strs.add(String.format("\"%s\": %s", name, printJson(objNode.get(name), indentation + "  ")));
            }
            // Taking care of the indentation of the first line in called segment. Indentation needed is
            // one level deeper than passed by indentation variable. 
            sb.append(indentation).append("  ").append(String.join(","+NL+indentation+"  ", strs));
            sb.append(NL).append(indentation).append("}");
    } else {
            // Empty objects are exit points of the recursion.
            sb.append("{}");
      }
    }
    else if(node.isArray()) {
        ArrayNode arrNode = (ArrayNode) node;
        if (arrNode.size() > 0){
            // Indentation of the first line is taken care of by the section calling it.
            sb.append("[").append(NL);
            for(Node aNode : arrNode) {
                strs.add(printJson(aNode, indentation + "  "));
            }
            // Indentation needed here is the saved indentation level + one more level. This was
            // simply easier to build than removing padding from the indentation string for other parts.
            sb.append(indentation).append("  ").append(String.join(","+NL+indentation+"  ", strs));
            sb.append(NL).append(indentation).append("]");
        } else {
            // Empty arrays are exit points of recursion.
            sb.append("[]");
        }
    }
    else if(node.isValue()) {
      ValueNode valNode = (ValueNode) node;
      // Value nodes are exit points of the recursion.
      sb.append(getValueNodeString(valNode));
    }
    return sb.toString();
 }
 
 /**
  * Find the valueNode's stored value and return it as a string.
  * @param node
  * @return 
  */
 private String getValueNodeString(ValueNode node){
    String valStr = "null";
    if(node.isNumber()) {
    valStr = numberToString(node.getNumber());
    }
    else if(node.isBoolean()) {
    valStr = Boolean.toString(node.getBoolean());
    }
    else if(node.isString()) {
    valStr = "\"" + node.getString() + "\"";
    }
    return valStr;
 }

  private static final String NL = System.lineSeparator();

  private static String numberToString(Double d) {
    String str = Double.toString(d);
    if(str.endsWith(".0")) {
      str = str.substring(0, str.length() - 2);
    }
    return str;
  }

  private void printSimple(Node node, StringBuilder sb) {
    if(node.isObject()) {
      sb.append("ObjectNode").append(NL);
      ObjectNode objNode = (ObjectNode) node;
      for(String name : objNode) {
        sb.append(name).append(": ");
        printSimple(objNode.get(name), sb);
      }
    }
    else if(node.isArray()) {
      sb.append("ArrayNode").append(NL);
      ArrayNode arrNode = (ArrayNode) node;
      for(Node aNode : arrNode) {
        printSimple(aNode, sb);
      }
    }
    else if(node.isValue()) {
      ValueNode valNode = (ValueNode) node;
      String typeStr = "NullValue";
      String valStr = "null";
      if(valNode.isNumber()) {
        typeStr = "NumberValue";
        valStr = numberToString(valNode.getNumber());
      }
      else if(valNode.isBoolean()) {
        typeStr = "BooleanValue";
        valStr = Boolean.toString(valNode.getBoolean());
      }
      else if(valNode.isString()) {
        typeStr = "StringValue";
        valStr = "\"" + valNode.getString() + "\"";
      }
      sb.append(String.format("%s(%s)%n", typeStr, valStr));
    }
  }
}
