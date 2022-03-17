package io.github.oliviercailloux.mido_svg.paper;

/**
 * This class is used to create different format of paper. It's based on the pattern factory
 *
 * @author Cocolollipop
 *
 */
public class FactoryPaper {

  public enum TypeFormat {
    A3, A4, Other

  }

  /**
   * Current possibilities for format's settings
   */
  private Enum<?> format;

  public FactoryPaper() {

  }

  public Enum<?> getFormat() {
    return format;
  }

  public Paper getPaper(Enum<?> settings) {
    if (settings == TypeFormat.A3) {
      return new PaperA3();
    } else if (settings == TypeFormat.A4) {
      return new PaperA4();
    }
    return null;
  }

  public Paper getPaper(Enum<?> settings, int x, int y) {
    if (settings == TypeFormat.Other) {
      return new PaperOther(x, y);
    }

    return null;

  }

  public void setFormat(Enum<?> format) {
    this.format = format;
  }
}
