package util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * <pre>
 * �Զ����ToString���������ڲ���ȥ����ֵ���Ե��ַ���
 * Created by Binary Wang on 2016-10-27.
 * @author <a href="https://github.com/binarywang">binarywang(Binary Wang)</a>
 * </pre>
 */
public class ToStringUtils {
  public static final ToStringStyle THE_STYLE = new SimpleMultiLineToStringStyle();
  private static class SimpleMultiLineToStringStyle extends ToStringStyle {
    private static final long serialVersionUID = 4645306494220335355L;
    private static final String LINE_SEPARATOR = "\n";
    private static final String NULL_TEXT = "<null>";

    public SimpleMultiLineToStringStyle() {
      super();
      this.setContentStart("[");
      this.setFieldSeparator(LINE_SEPARATOR + "  ");
      this.setFieldSeparatorAtStart(true);
      this.setContentEnd(LINE_SEPARATOR + "]");
      this.setNullText(NULL_TEXT);
      this.setUseShortClassName(true);
      this.setUseIdentityHashCode(false);
    }
  }

  /**
   * ���ڲ���ȥ����ֵ���Բ��Ի��з��ָ�����Լ�ֵ��toString�ַ���
   * @param obj
   */
  public static String toSimpleString(Object obj) {
    String toStringResult = ToStringBuilder.reflectionToString(obj, THE_STYLE);
    String[] split = toStringResult.split(SimpleMultiLineToStringStyle.LINE_SEPARATOR);
    StringBuilder result = new StringBuilder();
    for (String string : split) {
      if (string.endsWith(SimpleMultiLineToStringStyle.NULL_TEXT)) {
        continue;
      }

      result.append(string + SimpleMultiLineToStringStyle.LINE_SEPARATOR);
    }

    if (result.length() == 0) {
      return "";
    }

    //���û�зǿյ����ԣ������ <all null properties>
    if (StringUtils.countMatches(result, SimpleMultiLineToStringStyle.LINE_SEPARATOR) == 2) {
      return result.toString().split(SimpleMultiLineToStringStyle.LINE_SEPARATOR)[0]
        + "<all null values>]";
    }

    return result.deleteCharAt(result.length() - 1).toString();
  }
}
