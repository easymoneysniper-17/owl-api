package org.semanticweb.owlapi.model;

import org.semanticweb.owlapi.vocab.SWRLBuiltInsVocabulary;

import java.net.URI;
import java.util.List;
import java.util.Set;


/**
 * Author: Matthew Horridge<br>
 * The University Of Manchester<br>
 * Medical Informatics Group<br>
 * Date: Jan 15, 2007<br><br>
 * <p/>
 * matthew.horridge@cs.man.ac.uk<br>
 * www.cs.man.ac.uk/~horridgm<br><br>
 */
public interface SWRLDataFactory {

    /**
     * Gets a SWRL rule which is named with a URI
     * @param uri The rule URI (the "name" of the rule)
     * @param antecendent The atoms that make up the antecedent
     * @param consequent The atoms that make up the consequent
     */
    SWRLRule getSWRLRule(URI uri, Set<? extends SWRLAtom> antecendent, Set<? extends SWRLAtom> consequent);


    /**
     * Gets a SWRL rule.
     * @param uri The id
     * @param anonymous specifies if the rule is anonymous, <code>true</code> if it
     * is anonymous, otherwise <code>false</code>.
     * @param antededent The atoms that make up the antecedent
     * @param consequent The atoms that make up the consequent
     */
    SWRLRule getSWRLRule(URI uri, boolean anonymous, Set<? extends SWRLAtom> antededent, Set<? extends SWRLAtom> consequent);


    /**
     * Gets a SWRL rule which is not named with a URI.
     * @param antecendent The atoms that make up the antecedent
     * @param consequent The atoms that make up the consequent
     */
    SWRLRule getSWRLRule(Set<? extends SWRLAtom> antecendent, Set<? extends SWRLAtom> consequent);


    /**
     * Gets a SWRL class atom, i.e.  C(x) where C is a class expression and
     * x is either an individual id or an i-variable
     *
     * @param desc The class expression
     * @param arg  The argument (x)
     */
    SWRLClassAtom getSWRLClassAtom(OWLClassExpression desc, SWRLIArgument arg);


    /**
     * Gets a SWRL data range atom, i.e.  D(x) where D is an OWL data range and
     * x is either a constant or a d-variable
     *
     * @param rng The class expression
     * @param arg The argument (x)
     */
    SWRLDataRangeAtom getSWRLDataRangeAtom(OWLDataRange rng, SWRLDArgument arg);


    /**
     * Gets a SWRL object property atom, i.e. P(x, y) where P is an OWL object
     * property (expression) and x and y are are either an individual id or
     * an i-variable.
     * @param property The property (P)
     * @param arg0 The first argument (x)
     * @param arg1 The second argument (y)
     */
    SWRLObjectPropertyAtom getSWRLObjectPropertyAtom(OWLObjectPropertyExpression property,
                                                     SWRLIArgument arg0,
                                                     SWRLIArgument arg1);


     /**
     * Gets a SWRL data property atom, i.e. R(x, y) where R is an OWL data
     * property (expression) and x and y are are either a constant or
     * a d-variable.
     * @param property The property (P)
     * @param arg0 The first argument (x)
     * @param arg1 The second argument (y)
     */
    SWRLDataValuedPropertyAtom getSWRLDataPropertyAtom(OWLDataPropertyExpression property,
                                                       SWRLIArgument arg0,
                                                       SWRLDArgument arg1);


    /**
     * Creates a SWRL Built-In atom.
     *
     * @param builtIn The SWRL builtIn (see SWRL W3 member submission)
     * @param args       A non-empty set of SWRL D-Objects
     */
    SWRLBuiltInAtom getSWRLBuiltInAtom(SWRLBuiltInsVocabulary builtIn,
                                       List<SWRLDArgument> args);


    /**
     * Gets a SWRL i-variable.  This is used in rule atoms where a SWRL
     * I object can be used.
     * @param var The id (IRI) of the variable
     */
    SWRLIndividualVariable getSWRLIndividualVariable(IRI var);

    /**
     * Gets a SWRL d-variable.  This is used in rule atoms where a SWRL
     * D object can be used.
     * @param var The id (URI) of the variable
     */
    SWRLLiteralVariable getSWRLLiteralVariable(IRI var);


    /**
     * Gets a SWRL individual object.
     * @param individual The individual that is the object argument
     */
    SWRLIndividualArgument getSWRLIndividualArgument(OWLIndividual individual);

    /**
     * Gets a SWRL constant object.
     * @param literal The constant that is the object argument
     */
    SWRLLiteralArgument getSWRLLiteralArgument(OWLLiteral literal);

    SWRLSameAsAtom getSWRLSameAsAtom(SWRLIArgument arg0, SWRLIArgument arg1);

    SWRLDifferentFromAtom getSWRLDifferentFromAtom(SWRLIArgument arg0, SWRLIArgument arg1);
}