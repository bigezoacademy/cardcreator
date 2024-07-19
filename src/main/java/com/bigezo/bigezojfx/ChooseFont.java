
package com.bigezo.bigezojfx;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;


public class ChooseFont {
   static Font helveticaNormal16Red = new Font(Font.FontFamily.HELVETICA, 16,Font.NORMAL, new BaseColor(0, 0, 0));
  static Font  helveticaNormal16Black = new Font(Font.FontFamily.HELVETICA, 16,Font.BOLD, new BaseColor(197, 148, 45));

  static Font  helveticaNormal16Blue= new Font(Font.FontFamily.HELVETICA, 16,Font.NORMAL, new BaseColor(0, 17, 17));
  static Font  helveticaNormal16Green= new Font(Font.FontFamily.HELVETICA, 16,Font.NORMAL, new BaseColor(0, 17, 17));
  static Font  timesroman= new Font(Font.FontFamily.TIMES_ROMAN, 16,Font.BOLD, new BaseColor(0, 57, 230));
  static Font  symbol= new Font(Font.FontFamily.SYMBOL, 30,Font.BOLD, new BaseColor(0, 0, 0));
    static Font  helvetica= new Font(Font.FontFamily.HELVETICA, 20,Font.BOLD, new BaseColor(0, 0,0));

public Font myFont(String choiceOfFont){
  if (choiceOfFont.equals("times_roman"))  {
      return timesroman;
  }
  else if (choiceOfFont.equals("symbol"))  {
      return symbol;
  }
  else {
      return helvetica;
  }
};
    //cmyk colors below

    //Font churchFont= new Font(Font.FontFamily.TIMES_ROMAN, 45,
         //   Font.BOLD, BaseColor.BLACK);


}
