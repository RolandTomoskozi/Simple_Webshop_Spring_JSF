/**
 * Javascript Funktion zum pruefen, ob ajax-request erfolgreich waren.<br>
 *     Dies ist der FAll, wenn kein Validierungsfehler und kein unerwarteter Fehler aufgetreten ist.
 *     Wird z.B. verwendet, um nach Ajax-Aktion Popups bei Fehlern nicht zu schliessen
 *
 * @param args Rueckgabeinfo aus Primefaces-Ajax-Calls
 * @returns {boolean} true, wenn Aktion erfolgreich
 */
function operationSuccessful(args) {
    return !args.validationFailed && !args.unhandledError;
}