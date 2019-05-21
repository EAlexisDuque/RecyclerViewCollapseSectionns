package com.example.collapsingsection.models

/**
 * @author Alexis Duque on 2019-05-20.
 * @company Condor Labs
 * @email eduque@condorlabs.io
 */
data class RequirementItem(
    val itemTag: String,
    val title: String,
    val subtitle: String,
    val requirementsList: List<Requirement>
)
