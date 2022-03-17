package io.github.oliviercailloux.mido_svg.jUnit;

import static org.junit.Assert.assertEquals;

import io.github.oliviercailloux.mido_svg.database.DataBase;
import io.github.oliviercailloux.mido_svg.svg_generator.ResponsiveSVG;
import org.junit.Test;

public class GUIJUnitTest {

  /**
   * In this function we will test the function that count the number of formations in Parcours (or
   * Year) "M1"
   *
   */

  @Test
  public void GUItest() {

    ResponsiveSVG r = new ResponsiveSVG();
    DataBase data = new DataBase();
    int output = r.countFormations(data.getFormations(), 4);
    assertEquals(2, output);
  }
}
