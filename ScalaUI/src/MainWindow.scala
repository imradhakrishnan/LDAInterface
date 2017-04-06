import scala.swing._
import scala.io.Source
import scala.swing.FileChooser

class UI extends MainFrame {
  def restrictHeight(s: Component) {
    s.maximumSize = new Dimension(Short.MaxValue, s.preferredSize.height)
  }
  
  title = "Topic Modeling"
  var numberOfTopics = new TextField(columns = 5)
  var numberOfIterations = new TextField(columns = 5)
  val status = new TextArea { rows = 8; lineWrap = true; wordWrap = true }
  val submit = Button("Submit"){report()}
  val chooser = new FileChooser(new java.io.File("./"))
  val filePath = new TextField(columns = 10)
  var fileName = new String
  val inputFileButton = Button("Choose Stop Words File"){chooseFile()}
  
  restrictHeight(numberOfTopics)
  restrictHeight(numberOfIterations)
  restrictHeight(filePath)
  restrictHeight(inputFileButton)
  
  contents = new BoxPanel(Orientation.Vertical){
    contents += new BoxPanel(Orientation.Horizontal){
      contents += new Label("Number of Topics:")
      contents += Swing.HStrut(5)
      contents += numberOfTopics
      contents += Swing.HStrut(5)
      contents += new Label("Number of Iterations:")
      contents += numberOfIterations
    }
    contents += Swing.VStrut(5)
    contents += new BoxPanel(Orientation.Horizontal){
      contents += filePath
      contents += Swing.HStrut(5)
      contents += inputFileButton
    }
    contents += Swing.VStrut(5)
    contents += new Label("Status")
    contents += status
    contents += Swing.VStrut(5)
    contents += submit
    for (e <- contents)
      e.xLayoutAlignment = 0.0
    border = Swing.EmptyBorder(10, 10, 10, 10)
  }
  
  def report(){
    status.append("Number Of Topics: " + numberOfTopics.text + " ")
    status.append("Number of iterations: " + numberOfIterations.text)
  }
  def chooseFile() {
      if(chooser.showOpenDialog(null) == FileChooser.Result.Approve) {
         filePath.text = chooser.selectedFile.getAbsolutePath
      }
  }
}

object MainWindow {
  def main(args: Array[String]) {
    val ui = new UI
    ui.visible = true
    println("End of main function")
  }
}