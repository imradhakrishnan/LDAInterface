import scala.swing._
import scala.io.Source
import scala.swing.FileChooser

class ResultWindow extends MainFrame {
  def restrictHeight(s: Component) {
    s.maximumSize = new Dimension(Short.MaxValue, s.preferredSize.height)
  }
  
  title = "Topic Results"
  val topicRes = new TextArea { rows = 8; lineWrap = true; wordWrap = true }
  topicRes.editable = false
  val modifyTopics = new TextArea { rows = 2; lineWrap = true; wordWrap = true }
  modifyTopics.tooltip = "Include irrelevant topics as comma separated Eg. Topic1, Topic2 etc."
  val submit = Button("Submit"){}
  
  contents = new BoxPanel(Orientation.Vertical){
    contents += Swing.VStrut(5)
    contents += new Label("Identified Topics")
    contents += topicRes
    contents += Swing.VStrut(5)
    contents += new Label("Remove Topics")
    contents += modifyTopics
    contents += Swing.VStrut(5)
    contents += submit
    for (e <- contents)
      e.xLayoutAlignment = 0.0
    border = Swing.EmptyBorder(10, 10, 10, 10)
  }
}

object TopicResults {
  def main(args: Array[String]) {
    val res = new ResultWindow
    res.visible = true
    println("End of main function")
  }
}