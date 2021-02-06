package com.radixpro.enigma.dedoa.ui

import javafx.stage.Modality
import javafx.stage.Stage

class EphemeridesScreen {

    // texts
    private lateinit var txtTitle: String


    // general
    private val height = 500.0
    private val width = 600.0
    private val GAP = 6.0
    private val btnWidth = 200.0
    private lateinit var stage: Stage

    fun show() {
        initialize()
        stage = Stage()
        stage.minHeight = height
        stage.minWidth = width
        stage.initModality(Modality.APPLICATION_MODAL)
        stage.title = txtTitle
//        stage.scene = Scene(createVBox())
        stage.show()
    }

    private fun initialize() {
        defineTexts()
    }

    private fun defineTexts() {
        txtTitle = Rosetta.getText("ephemscreen.title")
    }

}