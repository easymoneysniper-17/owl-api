package org.semanticweb.owlapi.api.test.syntax;

import static org.junit.Assert.assertEquals;
import static org.semanticweb.owlapi.apibinding.OWLFunctionalSyntaxFactory.*;

import java.util.HashSet;
import java.util.Set;

import org.coode.owlapi.manchesterowlsyntax.ManchesterOWLSyntaxOntologyFormat;
import org.junit.Ignore;
import org.junit.Test;
import org.semanticweb.owlapi.api.test.Factory;
import org.semanticweb.owlapi.io.StringDocumentSource;
import org.semanticweb.owlapi.io.StringDocumentTarget;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDisjointUnionAxiom;
import org.semanticweb.owlapi.model.OWLOntology;

@SuppressWarnings("javadoc")
@Ignore
public class ManchesterOWLSyntaxDisjointUnionTestCase {
    @Test
    public void shouldRoundtripDisjointUnion() throws Exception {
        OWLDataFactory df = Factory.getFactory();
        OWLOntology o = Factory.getManager().createOntology();
        Set<OWLClass> disjoint = new HashSet<OWLClass>();
        disjoint.add(Class(IRI("http://iri/#b")));
        disjoint.add(Class(IRI("http://iri/#c")));
        disjoint.add(Class(IRI("http://iri/#d")));
        OWLDisjointUnionAxiom axiom = df.getOWLDisjointUnionAxiom(
                Class(IRI("http://iri/#a")), disjoint);
        o.getOWLOntologyManager().addAxiom(o, axiom);
        StringDocumentTarget target = new StringDocumentTarget();
        o.getOWLOntologyManager().saveOntology(o,
                new ManchesterOWLSyntaxOntologyFormat(), target);
        OWLOntology roundtripped = Factory.getManager().loadOntologyFromOntologyDocument(
                new StringDocumentSource(target.toString()));
        assertEquals(o.getLogicalAxioms(), roundtripped.getLogicalAxioms());
    }
}
