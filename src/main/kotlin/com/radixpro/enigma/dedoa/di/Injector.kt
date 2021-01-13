package com.radixpro.enigma.dedoa.di

import com.radixpro.enigma.dedoa.ui.Dashboard

object Injector {

    fun injectDashboard(): Dashboard {
        return Dashboard()
    }
}