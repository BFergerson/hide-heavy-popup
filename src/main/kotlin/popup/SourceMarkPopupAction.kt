package popup

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.popup.JBPopupFactory
import com.intellij.openapi.util.Disposer
import com.intellij.ui.jcef.JBCefBrowser
import com.intellij.ui.popup.AbstractPopup
import org.jetbrains.annotations.NotNull
import java.awt.Dimension
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent

open class SourceMarkPopupAction : AnAction() {

    override fun update(@NotNull e: AnActionEvent) {
        val project: Project? = e.project
        e.presentation.isEnabledAndVisible = project != null
    }

    override fun actionPerformed(@NotNull e: AnActionEvent) {
        //--------------- Attempt 1 ---------------
//        val browser = JBCefBrowser()
//        browser.loadHTML("<html><body><h1>Hello World</h1></body></html>")
//        browser.component.preferredSize = Dimension(400, 400)
//        val popup = JBPopupFactory.getInstance()
//            .createComponentPopupBuilder(browser.component, browser.component)
//            .setShowBorder(false)
//            .setShowShadow(false)
//            .setRequestFocus(true)
//            .setCancelOnWindowDeactivation(true)
//            .createPopup()
//        popup.showInFocusCenter()

        //--------------- Attempt 2 ---------------
        val browser = JBCefBrowser()
        browser.loadHTML("<html><body><h1>Hello World</h1></body></html>")
        browser.component.preferredSize = Dimension(400, 400)
        val popup = JBPopupFactory.getInstance()
            .createComponentPopupBuilder(browser.component, browser.component)
            .setShowBorder(false)
            .setShowShadow(false)
            .setRequestFocus(true)
            .setCancelOnWindowDeactivation(false)
            .createPopup()
        popup.showInFocusCenter()

        //doesn't work
        (popup as AbstractPopup).popupWindow.addWindowListener(object : WindowAdapter() {
            override fun windowClosing(p0: WindowEvent) {
                println("windowClosing: $p0")
                Disposer.dispose(popup)
            }

            override fun windowClosed(p0: WindowEvent) {
                println("windowClosed: $p0")
                Disposer.dispose(popup)
            }
        })
    }
}