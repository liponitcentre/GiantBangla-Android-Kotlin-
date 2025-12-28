package com.example.giantbangla

sealed class Screens(var screen: String) {
    data object OnboardingOne : Screens("onboardingOne")
}