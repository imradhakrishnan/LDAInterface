import scala.swing._
import scala.io.Source
import scala.swing.FileChooser

class ResultWindow extends MainFrame {
  def restrictHeight(s: Component) {
    s.maximumSize = new Dimension(Short.MaxValue, s.preferredSize.height)
  }
  
  title = "Topic Results"
  //set of unique words from topics 
  var words = Array("word1", "word2", "word3")
  
  val srcList = new ListView(words)
  var destList = new ListView(Array(""))

  srcList.border = javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK)
  destList.border = javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK)
  val leftBt = Button(">>"){LtoR()}
  val rightBt = Button("<<"){RtoL()}
  
  val topicRes = new TextArea { rows = 8; lineWrap = true; wordWrap = true }
  topicRes.editable = false
  val submit = Button("Submit"){recordWords()}

  restrictHeight(leftBt)
  restrictHeight(rightBt)
  
  val subPanel = new GridBagPanel {
    def constraints(x: Int, y:Int, 
      gridwidth: Int = 1, gridheight: Int = 1, 
      weightx: Double = 0.0, weighty: Double = 0.0,
      fill:GridBagPanel.Fill.Value = GridBagPanel.Fill.None)
    : Constraints = {
      val c = new Constraints
      c.gridx = x
      c.gridy = y
      c.gridwidth = gridwidth
      c.gridheight = gridheight
      c.weightx = weightx
      c.weighty = weighty
      c.fill = fill
      c
    }
    
    add(srcList, constraints(0,0, weightx=3.0, weighty = 3.0, fill=GridBagPanel.Fill.Both))
    add(destList, constraints(1,0, weightx=3.0, weighty = 3.0, fill=GridBagPanel.Fill.Both))
    add(leftBt, constraints(0,1, fill=GridBagPanel.Fill.Both))
    add(rightBt, constraints(1,1, fill=GridBagPanel.Fill.Both))
  }
 
  
  contents = new BoxPanel(Orientation.Vertical){
    contents += Swing.VStrut(5)
    contents += new Label("Identified Topics")
    contents += topicRes
    contents += Swing.VStrut(5)
    contents += subPanel
    contents += Swing.VStrut(5)
    contents += submit
    for (e <- contents)
      e.xLayoutAlignment = 0.0
    border = Swing.EmptyBorder(10, 10, 10, 10)
  }
  
  def LtoR(){
    destList.listData = destList.listData ++ srcList.selection.items.self
    var set1 = srcList.listData.toSet
    var set2 = srcList.selection.items.self.toSet
    srcList.listData = (set1 -- set2).toSeq
  }
  
  def RtoL(){
    srcList.listData = srcList.listData ++ destList.selection.items.self
    var set1 = destList.listData.toSet
    var set2 = destList.selection.items.self.toSet
    destList.listData = (set1 -- set2).toSeq
  }
  
  //function to get words from right side list and pass it to backend
  def recordWords(){
    //There is an empty string in the destList - remove it
    var finalwords = destList.listData.drop(1) 
    println(finalwords)
  }
}

object TopicResults {
  def main(args: Array[String]) {
    val res = new ResultWindow
    res.visible = true
    println("End of main function")
  }
}