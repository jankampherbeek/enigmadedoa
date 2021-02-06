/*
 *
 *  * Jan Kampherbeek, (c) 2021.
 *  * EnigmaDedOA is open source.
 *  * Please check the file copyright.txt in the root of the source for further details.
 *
 */

package com.radixpro.enigma.dedoa.ui

import javafx.application.Platform
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.ButtonBar
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.GridPane
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import javafx.scene.text.TextAlignment
import javafx.stage.Modality
import javafx.stage.Stage


class Dashboard(private val calculationScreen: CalculationScreen,
                private val comparisonScreen: ComparisonScreen,
                private val formulaeScreen: FormulaeScreen,
                private val ephemeridesScreen: EphemeridesScreen) {

    // texts
    private lateinit var txtBtnCalc: String
    private lateinit var txtBtnCompare: String
    private lateinit var txtBtnFormulae: String
    private lateinit var txtBtnEphemeris: String
    private lateinit var txtBtnExit: String
    private lateinit var txtBtnHelp: String
    private lateinit var txtBtnLanguage: String
    private lateinit var txtInfo: String
    private lateinit var txtInstruct: String
    private lateinit var lblCalc: Label
    private lateinit var lblCompare: Label
    private lateinit var lblEphemeris: Label
    private lateinit var lblFormulae: Label
    private lateinit var txtTitle: String



    // buttons
    private lateinit var btnCalc: Button
    private lateinit var btnCompare: Button
    private lateinit var btnEphemeris: Button
    private lateinit var btnExit: Button
    private lateinit var btnFormulae: Button
    private lateinit var btnHelp: Button
    private lateinit var btnLanguage: Button



    // general
    private val height = 380.0
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
        stage.scene = Scene(createVBox())
        stage.show()
    }

    fun initialize() {
        defineTexts()
        defineButtons()

//        checkStatus()
    }

    private fun defineTexts() {
        txtTitle = Rosetta.getText("dashboard.title")
        txtInfo = Rosetta.getText("dashboard.lbl_info")
        txtInstruct = Rosetta.getText("dashboard.lbl_instruct")
        txtBtnCalc = Rosetta.getText("dashboard.btn_calc")
        txtBtnCompare = Rosetta.getText("dashboard.btn_compare")
        txtBtnEphemeris = Rosetta.getText("dashboard.btn_ephemeris")
        txtBtnFormulae = Rosetta.getText("dashboard.btn_formulae")
        txtBtnExit = Rosetta.getText("shared.btn_exit")
        txtBtnHelp = Rosetta.getText("shared.btn_help")
        txtBtnLanguage = Rosetta.getText("shared.btn_language")
        lblCalc = LabelBuilder().setText(Rosetta.getText("dashboard.lbl_calc")).setPrefWidth(250.0).build()
        lblCompare = LabelBuilder().setText(Rosetta.getText("dashboard.lbl_compare")).setPrefWidth(250.0).build()
        lblEphemeris = LabelBuilder().setText(Rosetta.getText("dashboard.lbl_ephemeris")).setPrefWidth(250.0).build()
        lblFormulae = LabelBuilder().setText(Rosetta.getText("dashboard.lbl_formulae")).setPrefWidth(250.0).build()
    }

    private fun defineButtons() {
        btnCalc = ButtonBuilder(txtBtnCalc).setDisabled(false).setFocusTraversable(true).setPrefWidth(btnWidth).build()
        btnCompare = ButtonBuilder(txtBtnCompare).setDisabled(false).setFocusTraversable(true).setPrefWidth(btnWidth).build()
        btnFormulae = ButtonBuilder(txtBtnFormulae).setDisabled(false).setFocusTraversable(true).setPrefWidth(btnWidth).build()
        btnEphemeris = ButtonBuilder(txtBtnEphemeris).setDisabled(false).setFocusTraversable(true).setPrefWidth(btnWidth).build()
        btnExit = ButtonBuilder(txtBtnExit).setDisabled(false).setFocusTraversable(true).build()
        btnHelp = ButtonBuilder(txtBtnHelp).setDisabled(false).setFocusTraversable(true).build()
        btnLanguage = ButtonBuilder(txtBtnLanguage).setDisabled(false).setFocusTraversable(true).setPrefWidth(btnWidth).build()
        btnLanguage.onAction = EventHandler { onLanguage() }
        btnExit.onAction = EventHandler { onExit() }
        btnHelp.onAction = EventHandler { onHelp() }
        btnCalc.onAction = EventHandler { onCalculate()}
        btnCompare.onAction = EventHandler { onCompare() }
        btnFormulae.onAction = EventHandler { onFormulae() }
        btnEphemeris.onAction = EventHandler { onEphemerides() }
    }


    private fun createVBox(): VBox {
        return VBoxBuilder().setPadding(GAP).setHeight(height + 2 * GAP).setWidth(width + 2 * GAP).setChildren(arrayOf(createGridPane())).build()
    }

    private fun createGridPane(): GridPane {
        val grid = GridPaneBuilder().setHGap(GAP).setVGap(GAP).setPrefWidth(width).setPrefHeight(height).setStyleSheet(styleSheet).build()
        grid.add(createTitlePane(), 0, 0, 3, 1)
        grid.add(createImagePane(), 0, 1, 1, 1)
        grid.add(createInstructionPane(), 1, 1, 2, 1)
        grid.add(createInfoPane(), 0, 2, 1, 4)
        grid.add(lblCalc, 1, 2, 1, 1)
        grid.add(btnCalc, 2, 2, 1, 1)
        grid.add(lblCompare, 1, 3, 1, 1)
        grid.add(btnCompare, 2, 3, 1, 1)
        grid.add(lblFormulae, 1, 4, 1, 1)
        grid.add(btnFormulae, 2, 4, 1, 1)
        grid.add(lblEphemeris, 1, 5, 1, 1)
        grid.add(btnEphemeris, 2, 5, 1, 1)
        grid.add(PaneBuilder().setHeight(24.0).build(), 0, 6, 3, 1)
        grid.add(createBtnBar(), 0, 7, 3, 1)
        return grid
    }

    private fun createTitlePane(): Pane {
        val lblTitle = LabelBuilder().setText(txtTitle).setPrefWidth(width).setAlignment(Pos.CENTER).setStyleClass("titletext").build()
        return PaneBuilder().setHeight(57.0).setWidth(width).setStyleClass("titlepane").setChildren(arrayOf(lblTitle)).build()
    }

    private fun createInstructionPane(): Pane {
        val lblInstruction = LabelBuilder().setText(txtInstruct).setPrefWidth(width - 250.0).setAlignment(Pos.CENTER).build()
        lblInstruction.isWrapText = true
        lblInstruction.textAlignment = TextAlignment.CENTER
        val pane = PaneBuilder().setHeight(100.0).setWidth(width - 250.0).setChildren(arrayOf(lblInstruction)).build()
        pane.style = "-fx-background-color: lightblue;"
        return pane
    }

    private fun createInfoPane(): Pane {
        val lblInfo = LabelBuilder().setText(txtInfo).setPrefWidth(200.0).setAlignment(Pos.CENTER).build()
        lblInfo.isWrapText = true
        lblInfo.textAlignment = TextAlignment.CENTER
        return PaneBuilder().setWidth(250.0).setChildren(arrayOf(lblInfo)).build()
    }

    private fun createImagePane(): Pane {
        val image = Image("img/ziggurat.png")
        val imageView = ImageView(image)
        imageView.fitWidth = 223.0
        imageView.fitHeight = 86.0
        imageView.isPickOnBounds = true
        imageView.isPreserveRatio = true
        val pane = PaneBuilder().setWidth(250.0).setHeight(100.0).build()
        pane.children.add(imageView)
        return pane
    }

    private fun createBtnBar(): ButtonBar {
        return ButtonBarBuilder().setButtons(arrayOf(btnLanguage, btnHelp, btnExit)).build()
    }

    private fun onCalculate() {
        calculationScreen.show()
    }

    private fun onCompare() {
        comparisonScreen.show()
    }

    private fun onFormulae() {
        formulaeScreen.show()
    }

    private fun onEphemerides() {
        ephemeridesScreen.show()
    }

    private fun onLanguage() {
        stage.close()
        Rosetta.changeLanguage()
        show()
    }

    private fun onHelp() {
        Help(Rosetta.getHelpText("help.dashboard.title"), Rosetta.getHelpText("help.dashboard.content")).showContent()
    }

    private fun onExit() {
        Platform.exit()
    }



}