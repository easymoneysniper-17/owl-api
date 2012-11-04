package org.semanticweb.owlapi.contract;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.coode.owlapi.owlxmlparser.AbstractClassExpressionElementHandler;
import org.coode.owlapi.owlxmlparser.AbstractIRIElementHandler;
import org.coode.owlapi.owlxmlparser.AbstractOWLAxiomElementHandler;
import org.coode.owlapi.owlxmlparser.AbstractOWLDataRangeHandler;
import org.coode.owlapi.owlxmlparser.AbstractOWLIndividualOperandAxiomElementHandler;
import org.coode.owlapi.owlxmlparser.OWLAnnotationElementHandler;
import org.coode.owlapi.owlxmlparser.OWLAnnotationPropertyElementHandler;
import org.coode.owlapi.owlxmlparser.OWLAnonymousIndividualElementHandler;
import org.coode.owlapi.owlxmlparser.OWLDataPropertyElementHandler;
import org.coode.owlapi.owlxmlparser.OWLDatatypeElementHandler;
import org.coode.owlapi.owlxmlparser.OWLElementHandler;
import org.coode.owlapi.owlxmlparser.OWLLiteralElementHandler;
import org.coode.owlapi.owlxmlparser.OWLObjectPropertyElementHandler;
import org.coode.owlapi.owlxmlparser.OWLSubAnnotationPropertyOfElementHandler;
import org.coode.owlapi.owlxmlparser.OWLSubClassAxiomElementHandler;
import org.coode.owlapi.owlxmlparser.OWLXMLParserException;
import org.coode.owlapi.owlxmlparser.OWLXMLParserHandler;
import org.coode.owlapi.owlxmlparser.SWRLAtomElementHandler;
import org.coode.owlapi.owlxmlparser.SWRLAtomListElementHandler;
import org.coode.owlapi.owlxmlparser.SWRLVariableElementHandler;
import org.coode.owlapi.rdfxml.parser.AnonymousNodeChecker;
import org.coode.owlapi.rdfxml.parser.OWLRDFConsumer;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.semanticweb.owlapi.api.test.Factory;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLParserException;
import org.semanticweb.owlapi.io.RDFOntologyFormat;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.BufferingMode;
import org.semanticweb.owlapi.reasoner.Node;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.SimpleConfiguration;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasoner;
import org.semanticweb.owlapi.util.OWLClassExpressionVisitorExAdapter;
import org.semanticweb.owlapi.util.OWLObjectVisitorExAdapter;
import org.semanticweb.owlapi.vocab.OWL2Datatype;
import org.semanticweb.owlapi.vocab.OWLRDFVocabulary;

import uk.ac.manchester.cs.owl.owlapi.CollectionContainer;
import uk.ac.manchester.cs.owl.owlapi.CollectionContainerVisitor;

@SuppressWarnings({ "unused", "javadoc", "unchecked" })
public class Utils {
    static final IRI fakeiri1 = IRI.create("urn:aFake1");
    static final IRI fakeiri2 = IRI.create("urn:aFake2");
    static final IRI fakeiriproperty = IRI.create("urn:aFakeproperty");
    public static AxiomType<OWLAnnotationAssertionAxiom> mockAxiomType() {
        return AxiomType.ANNOTATION_ASSERTION;
    }

    public static <T> Collection<T> mockCollection(final T... values) {
        return new ArrayList<T>();
    }

    public static <T> Set<T> mockSet(final T... values) {
        return new HashSet<T>();
    }

    public static Set<Set<OWLAxiom>> mockSetSetAxiom() {
        return new HashSet<Set<OWLAxiom>>();
    }

    public static <T> List<T> mockList(final T... values) {
        return new ArrayList<T>();
    }

    public static <T extends OWLObject> Node<T> mockNode(final Class<T> classT) {
        return Mockito.mock(Node.class);
    }

    public static <T extends OWLObject> NodeSet<T> mockNodeSet(final Class<T> classT) {
        return Mockito.mock(NodeSet.class);
    }

    public static SWRLObjectVisitorEx<OWLObject> mockSWRLObject() {
        return new SWRLObjectVisitorEx<OWLObject>() {
            @Override
            public OWLObject visit(final SWRLRule node) {
                return null;
            }

            @Override
            public OWLObject visit(final SWRLClassAtom node) {
                return null;
            }

            @Override
            public OWLObject visit(final SWRLDataRangeAtom node) {
                return null;
            }

            @Override
            public OWLObject visit(final SWRLObjectPropertyAtom node) {
                return null;
            }

            @Override
            public OWLObject visit(final SWRLDataPropertyAtom node) {
                return null;
            }

            @Override
            public OWLObject visit(final SWRLBuiltInAtom node) {
                return null;
            }

            @Override
            public OWLObject visit(final SWRLVariable node) {
                return null;
            }

            @Override
            public OWLObject visit(final SWRLIndividualArgument node) {
                return null;
            }

            @Override
            public OWLObject visit(final SWRLLiteralArgument node) {
                return null;
            }

            @Override
            public OWLObject visit(final SWRLSameIndividualAtom node) {
                return null;
            }

            @Override
            public OWLObject visit(final SWRLDifferentIndividualsAtom node) {
                return null;
            }
        };
    }

    public static <T> CollectionContainerVisitor<T> mockCollContainer() {
        return new CollectionContainerVisitor<T>() {
            @Override
            public void visitItem(final T c) {}

            @Override
            public void visit(final CollectionContainer<T> c) {}
        };
    }

    public static OWLXMLParserHandler mockHandler() throws OWLParserException {
        final OWLXMLParserHandler mock = mock(OWLXMLParserHandler.class);
        Mockito.when(mock.getIRI(Matchers.anyString())).thenReturn(
                IRI.create("urn:otherfake"));
        Mockito.when(mock.getDataFactory()).thenReturn(mock(OWLDataFactory.class));
        Mockito.when(mock.getOWLOntologyManager()).thenReturn(Utils.getMockManager());
        Mockito.when(mock.getOntology()).thenReturn(getMockOntology());
        return mock;
    }

    public static OWLElementHandler<OWLObject> mockElementHandler()
            throws OWLXMLParserException {
        final OWLElementHandler<OWLObject> mock = mock(OWLElementHandler.class);
        Mockito.when(mock.getOWLObject()).thenReturn(
                OWLManager.getOWLDataFactory().getOWLThing());
        return mock;
    }

    public static AbstractOWLIndividualOperandAxiomElementHandler mockIndividualHandler()
            throws OWLParserException {
        final AbstractOWLIndividualOperandAxiomElementHandler mock = mock(AbstractOWLIndividualOperandAxiomElementHandler.class);
        Mockito.when(mock.getIRIFromAttribute(Matchers.anyString(), Matchers.anyString()))
                .thenReturn(IRI.create("urn:otherfake"));
        Mockito.when(mock.getOWLObject()).thenReturn(
                OWLManager.getOWLDataFactory().getOWLDeclarationAxiom(
                        OWLManager.getOWLDataFactory()
                                .getOWLClass(IRI.create("urn:fake"))));
        return mock;
    }

    public static OWLAnnotationElementHandler mockAnnotationHandler()
            throws OWLParserException {
        final OWLAnnotationElementHandler mock = mock(OWLAnnotationElementHandler.class);
        Mockito.when(mock.getIRIFromAttribute(Matchers.anyString(), Matchers.anyString()))
                .thenReturn(IRI.create("urn:otherfake"));
        Mockito.when(mock.getOWLObject()).thenReturn(
                OWLManager.getOWLDataFactory().getOWLAnnotation(
                        OWLManager.getOWLDataFactory().getRDFSLabel(),
                        OWLManager.getOWLDataFactory().getOWLLiteral(0)));
        return mock;
    }

    public static AbstractOWLDataRangeHandler mockDataRangeHandler()
            throws OWLParserException {
        final AbstractOWLDataRangeHandler mock = mock(AbstractOWLDataRangeHandler.class);
        Mockito.when(mock.getIRIFromAttribute(Matchers.anyString(), Matchers.anyString()))
                .thenReturn(IRI.create("urn:otherfake"));
        Mockito.when(mock.getOWLObject()).thenReturn(mock(OWLDatatype.class));
        return mock;
    }

    public static OWLAnonymousIndividualElementHandler mockAnonymousIndividualHandler()
            throws OWLParserException {
        final OWLAnonymousIndividualElementHandler mock = mock(OWLAnonymousIndividualElementHandler.class);
        Mockito.when(mock.getIRIFromAttribute(Matchers.anyString(), Matchers.anyString()))
                .thenReturn(IRI.create("urn:otherfake"));
        Mockito.when(mock.getOWLObject()).thenReturn(
                OWLManager.getOWLDataFactory().getOWLAnonymousIndividual());
        return mock;
    }

    public static OWLLiteralElementHandler mockLiteralHandler() throws OWLParserException {
        final OWLLiteralElementHandler mock = mock(OWLLiteralElementHandler.class);
        Mockito.when(mock.getIRIFromAttribute(Matchers.anyString(), Matchers.anyString()))
                .thenReturn(IRI.create("urn:otherfake"));
        Mockito.when(mock.getOWLObject()).thenReturn(
                OWLManager.getOWLDataFactory().getOWLLiteral(false));
        return mock;
    }

    public static OWLSubAnnotationPropertyOfElementHandler
            mockSubAnnotationPropertyOfHandler() throws OWLParserException {
        final OWLSubAnnotationPropertyOfElementHandler mock = mock(OWLSubAnnotationPropertyOfElementHandler.class);
        Mockito.when(mock.getIRIFromAttribute(Matchers.anyString(), Matchers.anyString()))
                .thenReturn(IRI.create("urn:otherfake"));
        Mockito.when(mock.getOWLObject()).thenReturn(
                OWLManager.getOWLDataFactory().getOWLDeclarationAxiom(
                        OWLManager.getOWLDataFactory().getOWLThing()));
        return mock;
    }

    public static OWLSubClassAxiomElementHandler mockSubObjectPropertyChainHandler()
            throws OWLParserException {
        final OWLSubClassAxiomElementHandler mock = mock(OWLSubClassAxiomElementHandler.class);
        Mockito.when(mock.getIRIFromAttribute(Matchers.anyString(), Matchers.anyString()))
                .thenReturn(IRI.create("urn:otherfake"));
        Mockito.when(mock.getOWLObject()).thenReturn(
                OWLManager.getOWLDataFactory().getOWLDeclarationAxiom(
                        OWLManager.getOWLDataFactory().getOWLThing()));
        return mock;
    }

    public static OWLDatatypeElementHandler mockDatatypeFacetRestrictionHandler()
            throws OWLParserException {
        final OWLDatatypeElementHandler mock = mock(OWLDatatypeElementHandler.class);
        Mockito.when(mock.getIRIFromAttribute(Matchers.anyString(), Matchers.anyString()))
                .thenReturn(IRI.create("urn:otherfake"));
        Mockito.when(mock.getOWLObject()).thenReturn(
                OWLManager.getOWLDataFactory().getOWLDatatype(
                        OWL2Datatype.OWL_REAL.getIRI()));
        return mock;
    }

    public static OWLAnnotationPropertyElementHandler mockAnnotationPropertyHandler()
            throws OWLParserException {
        final OWLAnnotationPropertyElementHandler mock = mock(OWLAnnotationPropertyElementHandler.class);
        Mockito.when(mock.getIRIFromAttribute(Matchers.anyString(), Matchers.anyString()))
                .thenReturn(IRI.create("urn:otherfake"));
        Mockito.when(mock.getOWLObject()).thenReturn(
                OWLManager.getOWLDataFactory().getRDFSComment());
        return mock;
    }

    public static AbstractClassExpressionElementHandler mockClassHandler()
            throws OWLParserException {
        final AbstractClassExpressionElementHandler mock = mock(AbstractClassExpressionElementHandler.class);
        Mockito.when(mock.getIRIFromAttribute(Matchers.anyString(), Matchers.anyString()))
                .thenReturn(IRI.create("urn:otherfake"));
        Mockito.when(mock.getOWLObject()).thenReturn(
                OWLManager.getOWLDataFactory().getOWLThing());
        return mock;
    }

    public static OWLDataPropertyElementHandler mockDataPropertyHandler()
            throws OWLParserException {
        final OWLDataPropertyElementHandler mock = mock(OWLDataPropertyElementHandler.class);
        Mockito.when(mock.getIRIFromAttribute(Matchers.anyString(), Matchers.anyString()))
                .thenReturn(IRI.create("urn:otherfake"));
        Mockito.when(mock.getOWLObject()).thenReturn(
                OWLManager.getOWLDataFactory().getOWLTopDataProperty());
        return mock;
    }

    public static AbstractIRIElementHandler mockAbstractIRIHandler()
            throws OWLParserException {
        final AbstractIRIElementHandler mock = mock(AbstractIRIElementHandler.class);
        Mockito.when(mock.getIRIFromAttribute(Matchers.anyString(), Matchers.anyString()))
                .thenReturn(IRI.create("urn:otherfake"));
        Mockito.when(mock.getOWLObject()).thenReturn(IRI.create(""));
        return mock;
    }

    public static AbstractOWLAxiomElementHandler mockAxiomHandler()
            throws OWLParserException {
        final AbstractOWLAxiomElementHandler mock = mock(AbstractOWLAxiomElementHandler.class);
        Mockito.when(mock.getIRIFromAttribute(Matchers.anyString(), Matchers.anyString()))
                .thenReturn(IRI.create("urn:otherfake"));
        Mockito.when(mock.getOWLObject()).thenReturn(
                OWLManager.getOWLDataFactory().getOWLDeclarationAxiom(
                        OWLManager.getOWLDataFactory().getOWLTopObjectProperty()));
        return mock;
    }

    public static SWRLAtomElementHandler mockSWRLAtomHandler() throws OWLParserException {
        final SWRLAtomElementHandler mock = mock(SWRLAtomElementHandler.class);
        Mockito.when(mock.getIRIFromAttribute(Matchers.anyString(), Matchers.anyString()))
                .thenReturn(IRI.create("urn:otherfake"));
        Mockito.when(mock.getOWLObject()).thenReturn(mock(SWRLAtom.class));
        return mock;
    }

    public static SWRLVariableElementHandler mockSWRLVariableHandler()
            throws OWLParserException {
        final SWRLVariableElementHandler mock = mock(SWRLVariableElementHandler.class);
        Mockito.when(mock.getIRIFromAttribute(Matchers.anyString(), Matchers.anyString()))
                .thenReturn(IRI.create("urn:otherfake"));
        Mockito.when(mock.getOWLObject()).thenReturn(mock(SWRLVariable.class));
        return mock;
    }

    public static SWRLAtomListElementHandler mockSWRLAtomListHandler()
            throws OWLParserException {
        final SWRLAtomListElementHandler mock = mock(SWRLAtomListElementHandler.class);
        Mockito.when(mock.getIRIFromAttribute(Matchers.anyString(), Matchers.anyString()))
                .thenReturn(IRI.create("urn:otherfake"));
        Mockito.when(mock.getOWLObject()).thenReturn(mockList(mock(SWRLAtom.class)));
        return mock;
    }

    public static OWLObjectPropertyElementHandler mockObjectPropertyHandler()
            throws OWLParserException {
        final OWLObjectPropertyElementHandler mock = mock(OWLObjectPropertyElementHandler.class);
        Mockito.when(mock.getIRIFromAttribute(Matchers.anyString(), Matchers.anyString()))
                .thenReturn(IRI.create("urn:otherfake"));
        Mockito.when(mock.getOWLObject()).thenReturn(
                OWLManager.getOWLDataFactory().getOWLTopObjectProperty());
        return mock;
    }

    public static OWLAnnotationAxiomVisitorEx<OWLObject> mockAnnotationAxiom() {
        return new OWLAnnotationAxiomVisitorEx<OWLObject>() {
            @Override
            public OWLObject visit(final OWLAnnotationAssertionAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLSubAnnotationPropertyOfAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLAnnotationPropertyDomainAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLAnnotationPropertyRangeAxiom axiom) {
                return null;
            }
        };
    }

    public static OWLObjectVisitorEx<OWLObject> mockObject() {
        return new OWLObjectVisitorExAdapter<OWLObject>();
    }

    public static OWLClassExpressionVisitorEx<OWLObject> mockClassExpression() {
        return new OWLClassExpressionVisitorExAdapter<OWLObject>();
    }

    public static OWLAnnotationObjectVisitorEx<OWLObject> mockAnnotationObject() {
        return new OWLAnnotationObjectVisitorEx<OWLObject>() {
            @Override
            public OWLObject visit(final OWLAnnotationAssertionAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLSubAnnotationPropertyOfAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLAnnotationPropertyDomainAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLAnnotationPropertyRangeAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final IRI iri) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLAnonymousIndividual individual) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLLiteral literal) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLAnnotation node) {
                return null;
            }
        };
    }

    public static OWLAnnotationSubjectVisitorEx<OWLObject> mockAnnotationSubject() {
        return new OWLAnnotationSubjectVisitorEx<OWLObject>() {
            @Override
            public OWLObject visit(final IRI iri) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLAnonymousIndividual individual) {
                return null;
            }
        };
    }

    public static OWLAnnotationValueVisitorEx<OWLObject> mockAnnotationValue() {
        return new OWLAnnotationValueVisitorEx<OWLObject>() {
            @Override
            public OWLObject visit(final IRI iri) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLAnonymousIndividual individual) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLLiteral literal) {
                return null;
            }
        };
    }

    public static OWLDataRangeVisitorEx<OWLObject> mockDataRange() {
        return new OWLDataRangeVisitorEx<OWLObject>() {
            @Override
            public OWLObject visit(final OWLDatatype node) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLDataOneOf node) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLDataComplementOf node) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLDataIntersectionOf node) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLDataUnionOf node) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLDatatypeRestriction node) {
                return null;
            }
        };
    }

    public static OWLPropertyExpressionVisitorEx<OWLObject> mockPropertyExpression() {
        return new OWLPropertyExpressionVisitorEx<OWLObject>() {
            @Override
            public OWLObject visit(final OWLObjectProperty property) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLObjectInverseOf property) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLDataProperty property) {
                return null;
            }
        };
    }

    public static OWLLogicalAxiomVisitorEx<OWLObject> mockLogicalAxiom() {
        return new OWLLogicalAxiomVisitorEx<OWLObject>() {
            @Override
            public OWLObject visit(final OWLSubClassOfAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLNegativeObjectPropertyAssertionAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLAsymmetricObjectPropertyAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLReflexiveObjectPropertyAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLDisjointClassesAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLDataPropertyDomainAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLObjectPropertyDomainAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLEquivalentObjectPropertiesAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLNegativeDataPropertyAssertionAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLDifferentIndividualsAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLDisjointDataPropertiesAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLDisjointObjectPropertiesAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLObjectPropertyRangeAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLObjectPropertyAssertionAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLFunctionalObjectPropertyAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLSubObjectPropertyOfAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLDisjointUnionAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLSymmetricObjectPropertyAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLDataPropertyRangeAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLFunctionalDataPropertyAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLEquivalentDataPropertiesAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLClassAssertionAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLEquivalentClassesAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLDataPropertyAssertionAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLTransitiveObjectPropertyAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLIrreflexiveObjectPropertyAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLSubDataPropertyOfAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLInverseFunctionalObjectPropertyAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLSameIndividualAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLSubPropertyChainOfAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLInverseObjectPropertiesAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLHasKeyAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final SWRLRule rule) {
                return null;
            }
        };
    }

    public static OWLNamedObjectVisitorEx<OWLObject> mockNamedObject() {
        return new OWLNamedObjectVisitorEx<OWLObject>() {
            @Override
            public OWLObject visit(final OWLClass owlClass) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLObjectProperty property) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLDataProperty property) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLNamedIndividual owlIndividual) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLOntology ontology) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLDatatype datatype) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLAnnotationProperty property) {
                return null;
            }
        };
    }

    public static OWLEntity mockOWLEntity() {
        OWLEntity mock = mock(OWLEntity.class);
        when(mock.getIRI()).thenReturn(IRI.create("urn:fakeiri#fakefragment"));
        return mock;
    }

    public static OWLEntityVisitorEx<OWLObject> mockEntity() {
        return new OWLEntityVisitorEx<OWLObject>() {
            @Override
            public OWLObject visit(final OWLClass cls) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLObjectProperty property) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLDataProperty property) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLNamedIndividual individual) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLDatatype datatype) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLAnnotationProperty property) {
                return null;
            }
        };
    }

    public static OWLIndividualVisitorEx<OWLObject> mockIndividual() {
        return new OWLIndividualVisitorEx<OWLObject>() {
            @Override
            public OWLObject visit(final OWLNamedIndividual individual) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLAnonymousIndividual individual) {
                return null;
            }
        };
    }

    public static OWLOntologyChangeVisitorEx<OWLObject> mockOntologyChange() {
        return new OWLOntologyChangeVisitorEx<OWLObject>() {
            @Override
            public OWLObject visit(final AddAxiom change) {
                return null;
            }

            @Override
            public OWLObject visit(final RemoveAxiom change) {
                return null;
            }

            @Override
            public OWLObject visit(final SetOntologyID change) {
                return null;
            }

            @Override
            public OWLObject visit(final AddImport change) {
                return null;
            }

            @Override
            public OWLObject visit(final RemoveImport change) {
                return null;
            }

            @Override
            public OWLObject visit(final AddOntologyAnnotation change) {
                return null;
            }

            @Override
            public OWLObject visit(final RemoveOntologyAnnotation change) {
                return null;
            }
        };
    }

    public static OWLAxiomVisitorEx<OWLObject> mockAxiom() {
        return new OWLAxiomVisitorEx<OWLObject>() {
            @Override
            public OWLObject visit(final OWLSubAnnotationPropertyOfAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLAnnotationPropertyDomainAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLAnnotationPropertyRangeAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLSubClassOfAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLNegativeObjectPropertyAssertionAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLAsymmetricObjectPropertyAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLReflexiveObjectPropertyAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLDisjointClassesAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLDataPropertyDomainAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLObjectPropertyDomainAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLEquivalentObjectPropertiesAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLNegativeDataPropertyAssertionAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLDifferentIndividualsAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLDisjointDataPropertiesAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLDisjointObjectPropertiesAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLObjectPropertyRangeAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLObjectPropertyAssertionAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLFunctionalObjectPropertyAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLSubObjectPropertyOfAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLDisjointUnionAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLDeclarationAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLAnnotationAssertionAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLSymmetricObjectPropertyAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLDataPropertyRangeAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLFunctionalDataPropertyAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLEquivalentDataPropertiesAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLClassAssertionAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLEquivalentClassesAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLDataPropertyAssertionAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLTransitiveObjectPropertyAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLIrreflexiveObjectPropertyAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLSubDataPropertyOfAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLInverseFunctionalObjectPropertyAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLSameIndividualAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLSubPropertyChainOfAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLInverseObjectPropertiesAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLHasKeyAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLDatatypeDefinitionAxiom axiom) {
                return null;
            }

            @Override
            public OWLObject visit(final SWRLRule rule) {
                return null;
            }
        };
    }

    public static OWLDataVisitorEx<OWLObject> mockData() {
        return new OWLDataVisitorEx<OWLObject>() {
            @Override
            public OWLObject visit(final OWLDatatype node) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLDataComplementOf node) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLDataOneOf node) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLDataIntersectionOf node) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLDataUnionOf node) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLDatatypeRestriction node) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLLiteral node) {
                return null;
            }

            @Override
            public OWLObject visit(final OWLFacetRestriction node) {
                return null;
            }
        };
    }

    public static OWLOntologyManager getMockManager() {
        return OWLManager.createOWLOntologyManager();
    }

    public static OWLOntologyManager getRealMockManager() {
        final OWLOntologyManager mock = mock(OWLOntologyManager.class);
        when(mock.getOWLDataFactory()).thenReturn(OWLManager.getOWLDataFactory());
        return mock;
    }

    public static OWLOntologyManager getMockManagerMockFactory() {
        final OWLOntologyManager mock = mock(OWLOntologyManager.class);
        OWLDataFactory f = mock(OWLDataFactory.class);
        when(mock.getOWLDataFactory()).thenReturn(f);
        return mock;
    }

    public static OWLOntology getMockOntology() {
        try {
            return OWLManager.createOWLOntologyManager().createOntology();
        } catch (OWLOntologyCreationException e) {
            throw new RuntimeException(e);
        }
    }

    public static OWLClassExpression mockAnonClass() {
        OWLClassExpression mock = mock(OWLClassExpression.class);
        when(mock.isAnonymous()).thenReturn(true);
        return mock;
    }

    public static OWLRDFConsumer mockOWLRDFConsumer() {
        try {
            OWLOntologyManager man = Factory.getManager();
            OWLOntology mockOntology = man.createOntology(
                new OWLOntologyID(IRI.create("urn:test:test"), IRI
                        .create("urn:test:othertest")));
        OWLRDFConsumer c = new OWLRDFConsumer(mockOntology, new AnonymousNodeChecker() {
            @Override
            public boolean isAnonymousSharedNode(final String iri) {
                return false;
            }

            @Override
            public boolean isAnonymousNode(final String iri) {
                return false;
            }

            @Override
            public boolean isAnonymousNode(final IRI iri) {
                return false;
            }
        }, new OWLOntologyLoaderConfiguration());
        c.setOntologyFormat(new RDFOntologyFormat() {
            private static final long serialVersionUID = 30402L;
        });
            c.addTriple(fakeiri1,
                    OWLRDFVocabulary.OWL_ON_PROPERTY.getIRI(),
 fakeiri2);
        return c;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static OWLReasoner structReasoner() {
        OWLReasoner r;
        try {
            r = new StructuralReasoner(OWLManager.createOWLOntologyManager()
                    .createOntology(), new SimpleConfiguration(), BufferingMode.BUFFERING);
        } catch (OWLOntologyCreationException e) {
            throw new RuntimeException(e);
        }
        return r;
    }

    public static OWLObjectPropertyExpression mockObjectProperty() {
        return OWLManager.getOWLDataFactory().getOWLTopObjectProperty();
    }

    public static OWLLiteral mockLiteral() {
        return OWLManager.getOWLDataFactory().getOWLLiteral(false);
    }
}