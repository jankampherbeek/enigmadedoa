/*
 * Jan Kampherbeek, (c) 2020, 2021.
 * EnigmaDedVM is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.dedoa

import com.radixpro.enigma.dedoa.di.Injector
import javafx.application.Application
import javafx.stage.Stage

class StartEnigma: Application() {

    fun run(args: Array<String?>) {
        launch(*args)
    }

    override fun start(primaryStage: Stage) {
        Injector.injectDashboard().show()
    }

}