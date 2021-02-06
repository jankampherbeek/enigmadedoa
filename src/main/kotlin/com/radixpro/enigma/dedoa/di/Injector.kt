package com.radixpro.enigma.dedoa.di

import com.radixpro.enigma.dedoa.ui.*

object Injector {

    fun injectCalculationScreen(): CalculationScreen {
        return CalculationScreen()
    }

    fun injectComparisonScreen(): ComparisonScreen {
        return ComparisonScreen()
    }

    fun injectDashboard(): Dashboard {
        return Dashboard(injectCalculationScreen(), injectComparisonScreen(), injectFormulaeScreen(), injectEphemeridesScreen())
    }

    fun injectEphemeridesScreen(): EphemeridesScreen {
        return EphemeridesScreen()
    }

    fun injectFormulaeScreen(): FormulaeScreen {
        return FormulaeScreen()
    }
}