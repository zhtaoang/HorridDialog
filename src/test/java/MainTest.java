import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import org.junit.Test;

class TestStageAdapter implements StageAdapter {

  @Override
  public void setTitle(String s) {

  }

  @Override
  public void setScene(Scene scene) {

  }

  @Override
  public void show() {

  }
}

class TestableMain extends Main {
  private TestButtonAdapter buttonAdapter;

  @Override
  protected void setupDialog() {

  }

  @Override
  protected ButtonAdapter getButtonAdapter() {
    return this.buttonAdapter;
  }

  public void setButtonAdapter(TestButtonAdapter buttonAdapter) {
    this.buttonAdapter = buttonAdapter;
  }
}

class TestButtonAdapter implements ButtonAdapter {

  @Override
  public void setOnAction(EventHandler<ActionEvent> eventHandler) {

  }
}

public class MainTest {

  @Test
  public void ItTestsStuff() throws Exception {
    TestableMain main = new TestableMain();
    TestButtonAdapter buttonAdapter = new TestButtonAdapter();
    main.setButtonAdapter(buttonAdapter);

    main.startWithStageAdapter(new TestStageAdapter());
  }
}
