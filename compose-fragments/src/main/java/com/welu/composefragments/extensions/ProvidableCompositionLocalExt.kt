package com.welu.composefragments.extensions

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

@Composable
fun ProvidableCompositionLocal<Context>.getActivity() : FragmentActivity? = current.getActivity()

@Composable
fun ProvidableCompositionLocal<Context>.requireActivity() : FragmentActivity = current.requireActivity()

@Composable
fun ProvidableCompositionLocal<Context>.getNavHostFragment() : NavHostFragment? = current.getActivity()?.navHostFragment

@Composable
fun ProvidableCompositionLocal<Context>.requireNavHostFragment() : NavHostFragment = current.getActivity()?.navHostFragment!!

@Composable
fun ProvidableCompositionLocal<Context>.getNavController() : NavController? = current.getActivity()?.navController

@Composable
fun ProvidableCompositionLocal<Context>.requireNavController() : NavController = current.getActivity()?.navController!!