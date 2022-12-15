import org.objectweb.asm.*;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Arrays;

import static java.lang.Integer.parseInt;


public class ASMReader {
    static class SimpleAnnotationVisitor extends AnnotationVisitor{
        public SimpleAnnotationVisitor() {
            super(Opcodes.ASM9);
        }

        public void visit(java.lang.String name, java.lang.Object value){
            System.out.printf("an\tvisit\t%s\t%s\n",name,value);
        }

        public AnnotationVisitor visitAnnotation(java.lang.String name, java.lang.String descriptor){
            System.out.printf("an\tvisitAnnotation\t%s\t%s\n",name,descriptor);

            return new SimpleAnnotationVisitor();
        }

        public AnnotationVisitor visitArray(java.lang.String name){
            System.out.printf("an\tvisitArray\t%s\n",name);

            return new SimpleAnnotationVisitor();
        }

        public void visitEnd(){
            System.out.printf("an\tvisitEnd\n");
        }

        public void visitEnum(java.lang.String name, java.lang.String descriptor, java.lang.String value){
            System.out.printf("an\tvisitEnum\t%s\t%s\t%s\n",name,descriptor,value);
        }
    }

    static class SimpleFieldVisitor extends FieldVisitor{
        public SimpleFieldVisitor() {
            super(Opcodes.ASM9);
        }

        public AnnotationVisitor visitAnnotation(java.lang.String descriptor, boolean visible){
            System.out.printf("fi\tvisitAnnotation\t%s\t%b\n",descriptor,visible);

            return new SimpleAnnotationVisitor();
        }

        public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, java.lang.String descriptor, boolean visible){
            System.out.printf("fi\tvisitTypeAnnotation\t%d\t%s\t%s\t%b\n",typeRef, typePath.toString(), descriptor,visible);

            return new SimpleAnnotationVisitor();
        }

        public void visitAttribute(Attribute attribute){
            System.out.printf("fi\tvisitAttribute	%s\n",attribute.type);
        }

        public void visitEnd(){
            System.out.printf("fi\tvisitEnd\n");
        }
    }

    static class SimpleClassVisitor extends ClassVisitor {
        public SimpleClassVisitor() {
            super(Opcodes.ASM9);
        }

        public void visit(int version, int access, java.lang.String name, java.lang.String signature, java.lang.String superName, java.lang.String[] interfaces){
            System.out.printf("cl\tvisit\t%d\t%d\t%s\t%s\t%s",version,access,name,signature,superName);

            if(interfaces != null) {
                for (int i = 0; i < interfaces.length; i++) {
                    System.out.printf("\t%s", interfaces[i]);
                }
            }
            else System.out.printf("\tnull");
            System.out.printf("\n");
        }

        public AnnotationVisitor visitAnnotation(java.lang.String descriptor, boolean visible){
            System.out.printf("cl\tvisitAnnotation\t%s\t%b\n",descriptor,visible);

            return new SimpleAnnotationVisitor();
        }

        public void visitAttribute(Attribute attribute){
            System.out.printf("cl\tvisitAttribute	%s\n",attribute.type);
        }

        public void visitEnd(){
            System.out.printf("cl\tvisitEnd\n");
        }

        public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
            System.out.printf("cl\tvisitField	%d	%s	%s	%s %s\n",
                    access, name, desc, signature, value);

            return new SimpleFieldVisitor();
        }

        public void visitInnerClass(java.lang.String name, java.lang.String outerName, java.lang.String innerName, int access){

            System.out.printf("cl\tvisitInnerClass\t%s	%s	%s	%d\n",name,outerName,innerName,access);
        }

        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            System.out.printf("cl\tvisitMethod	%s	%s	%s", name, desc, signature);

            if(exceptions != null) {
                for (int i = 0; i < exceptions.length; i++) {
                    System.out.printf("\t%s",exceptions[i]);
                }
            }
            else System.out.printf("\tnull");
            System.out.printf("\n");


            return new SimpleMethodVisitor();
        }

        public ModuleVisitor visitModule(java.lang.String name, int access, java.lang.String version){
            System.out.printf("cl\tvisitModule	%s	%d	%s\n",name,access,version);

            return new SimpleModuleVisitor();
        }

        public void visitNestHost(java.lang.String nestHost){
            System.out.printf("cl\tvisitNestHost\t%s\n",nestHost);
        }

        public void visitNestMember(java.lang.String nestMember){
            System.out.printf("cl\tvisitNestMember	%s\n",nestMember);
        }

        public void visitOuterClass(java.lang.String owner, java.lang.String name, java.lang.String descriptor){
            System.out.printf("cl\tvisitOuterClass	%s	%s	%s\n",owner,name,descriptor);
        }

        public void visitPermittedSubclass(java.lang.String permittedSubclass){
            System.out.printf("cl\tvisitPermittedSubclass	%s\n",permittedSubclass);
        }

        public RecordComponentVisitor visitRecordComponent(java.lang.String name, java.lang.String descriptor, java.lang.String signature){
            System.out.printf("cl\tvisitRecordComponent	%s	%s	%s\n",name,descriptor,signature);

            return new SimpleRecordComponentVisitor();
        }

        public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, java.lang.String descriptor, boolean visible){
            System.out.printf("cl\tvisitTypeAnnotation	%d	%s	%s	%b\n",typeRef, typePath.toString(), descriptor,visible);

            return new SimpleAnnotationVisitor();
        }

        @Override
        public void visitSource(String source, String debug) {
            System.out.printf("cl\tvisitSource	%s	%s\n",source,debug);

            super.visitSource(source, debug);
        }

    }

    static class SimpleMethodVisitor extends MethodVisitor {
        public SimpleMethodVisitor() {
            super(Opcodes.ASM9);
        }

        public void visitVarInsn(int opcode, int var) {
            System.out.printf("me\tvisitVarInsn	%d	%d\n", opcode, var);
        }

        public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
            System.out.printf("me\tvisitLocalVariable	%s	%s	%s	%s\t%s\t%d\n",
                    name, desc, signature, "Start","End",index);
        }

        public void visitMaxs(int maxStack, int maxLocals) {
            System.out.printf("me\tvisitMaxs	%d	%d\n", maxStack, maxLocals);
        }

        public void visitAnnotableParameterCount(int parameterCount, boolean visible){
            System.out.printf("me\tvisitAnnotableParameterCount	%d	%b\n",parameterCount,visible);
        }

        public AnnotationVisitor visitAnnotation(java.lang.String descriptor, boolean visible){
            System.out.printf("me\tvisitAnnotation	%s	%b\n",descriptor,visible);

            return new SimpleAnnotationVisitor();
        }

        public AnnotationVisitor visitAnnotationDefault(){
            System.out.printf("me\tvisitAnnotationDefault\n");

            return new SimpleAnnotationVisitor();
        }

        public void visitAttribute(Attribute attribute){

            System.out.printf("me\tvisitAttribute	%s\n",attribute.type);
        }

        public void visitCode(){
            System.out.printf("me\tvisitCode\n");
        }

        public void visitEnd(){
            System.out.printf("me\tvisitEnd\n");
        }

        public void visitFieldInsn(int opcode, java.lang.String owner, java.lang.String name, java.lang.String descriptor){
            System.out.printf("me\tvisitFieldInsn	%d	%s	%s	%s\n",opcode,owner,name,descriptor);
        }

        public void visitFrame(int type, int numLocal, java.lang.Object[] local, int numStack, java.lang.Object[] stack){
            System.out.printf("me\tvisitFrame	%d	%d", type, numLocal);
            int i = 0;
            try {
                for (i = 0; i < local.length; i++) {
                    System.out.printf("\t%s",local[i].toString());
                }
            } catch (NullPointerException e){
                System.out.printf("\tdone");
            }

            System.out.printf("\t%d",numStack);
            try {
                for (i = 0; i < stack.length; i++) {
                    System.out.printf("\t%s",stack[i].toString());
                }
            } catch (NullPointerException e){
                System.out.printf("\tdone");
            }
            System.out.printf("\n");
        }

        public void visitIincInsn(int varIndex, int increment){
            System.out.printf("me\tvisitIincInsn	%d	%d\n",varIndex,increment);
        }

        public void visitInsn(int opcode){
            System.out.printf("me\tvisitInsn	%d\n", opcode);
        }

        public AnnotationVisitor visitInsnAnnotation(int typeRef, TypePath typePath, java.lang.String descriptor, boolean visible){
            System.out.printf("me\tvisitInsnAnnotation	%d	%s	%s	%b\n",typeRef, typePath.toString(), descriptor,visible);

            return new SimpleAnnotationVisitor();
        }

        public void visitIntInsn(int opcode, int operand){
            System.out.printf("me\tvisitIntInsn	%d	%d\n",opcode,operand);
        }

        public void visitInvokeDynamicInsn(java.lang.String name, java.lang.String descriptor, Handle bootstrapMethodHandle, java.lang.Object... bootstrapMethodArguments){

            System.out.printf("me\tvisitInvokeDynamicInsn\t%s\t%s\t%d\t%s\t%s\t%s\t%b",name,descriptor,bootstrapMethodHandle.getTag(),
                    bootstrapMethodHandle.getOwner(), bootstrapMethodHandle.getName(), bootstrapMethodHandle.getDesc(),bootstrapMethodHandle.isInterface());
            if(bootstrapMethodArguments != null) {
                for (int i = 0; i < bootstrapMethodArguments.length; i++) {
                    System.out.printf("\t%s",bootstrapMethodArguments[i].toString());
                }
            }
            else System.out.printf("\tnull");
            System.out.printf("\n");
        }

        public void visitJumpInsn(int opcode, Label label){
            System.out.printf("me\tvisitJumpInsn	%d\t%s\n",opcode,"Label");
        }

        public void visitLabel(Label label){
            System.out.printf("me\tvisitLabel\t%s\n","Label");
        }

        public void visitLdcInsn(java.lang.Object value){
            System.out.printf("me\tvisitLdcInsn %s\n",value);
        }

        public void visitLineNumber(int line, Label start){
            System.out.printf("me\tvisitLineNumber  %d  %s\n",line,"Start");
        }

        public AnnotationVisitor visitLocalVariableAnnotation(int typeRef, TypePath typePath, Label[] start, Label[] end,
                                                              int[] index, java.lang.String descriptor, boolean visible){
            System.out.printf("me\tvisitLocalVariableAnnotation\t%d\t%s",typeRef,typePath.toString());
            if(start != null) {
                for (int i = 0; i < start.length; i++) {
                    System.out.printf("\t%s","Start");
                }
            }
            System.out.printf("\tdone");
            if(end != null) {
                for (int i = 0; i < end.length; i++) {
                    System.out.printf("\t%s","End");
                }
            }
            System.out.printf("\tdone");
            if(index != null) {
                for (int i = 0; i < index.length; i++) {
                    System.out.printf("\t%s",index[i]);
                }
            }
            System.out.printf("\tnull");
            System.out.printf("\t%s\t%b\n",descriptor,visible);

            return new SimpleAnnotationVisitor();
        }

        public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels){
            System.out.printf("me\tvisitLookupSwitchInsn\t%s\n",dflt.toString());
            if(keys != null) {
                for (int i = 0; i < keys.length; i++) {
                    System.out.printf("\t%s",keys[i]);
                }
            }
            else System.out.printf("\tnull");
            if(labels != null) {
                for (int i = 0; i < labels.length; i++) {
                    System.out.printf("\t%s","Label");
                }
            }
            else System.out.printf("\tnull");
            System.out.printf("\n");
        }

        public void visitMethodInsn(int opcode, java.lang.String owner, java.lang.String name, java.lang.String descriptor, boolean isInterface){
            System.out.printf("me\tvisitMethodInsn\t%d\t%s\t%s\t%s\t%b\n",opcode,owner,name,descriptor,isInterface);
        }

        public void visitMultiANewArrayInsn(java.lang.String descriptor, int numDimensions){
            System.out.printf("me\tvisitMultiANewArrayInsn\t%s\t%d\n",descriptor,numDimensions);
        }

        public void visitParameter(java.lang.String name, int access){
            System.out.printf("me\tvisitParameter\t%s\t%d\n",name,access);
        }

        public AnnotationVisitor visitParameterAnnotation(int parameter, java.lang.String descriptor, boolean visible){
            System.out.printf("me\tvisitParameterAnnotation\t%d\t%s\t%b\n",parameter,descriptor,visible);

            return new SimpleAnnotationVisitor();
        }

        public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels){
            System.out.printf("me\tvisitTableSwitchInsn\t%d\t%d\t%s",min,max,"Label");
            if(labels != null) {
                for (int i = 0; i < labels.length; i++) {
                    System.out.printf("\t%s","Label");
                }
            }
            else System.out.printf("\tnull");
            System.out.printf("\n");
        }

        public AnnotationVisitor visitTryCatchAnnotation(int typeRef, TypePath typePath, java.lang.String descriptor, boolean visible){
            System.out.printf("me\tvisitTryCatchAnnotation\t%d\t%s\t%s\t%b\n",typeRef,typePath.toString(),descriptor,visible);

            return new SimpleAnnotationVisitor();
        }

        public void visitTryCatchBlock(Label start, Label end, Label handler, java.lang.String type){
            System.out.printf("me\tvisitTryCatchBlock\t%s\t%s\t%s\t%s\n","Start","End",handler.toString(),type);
        }

        public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, java.lang.String descriptor, boolean visible){
            System.out.printf("me\tvisitTypeAnnotation\t%d\t%s\t%s\t%b\n",typeRef,typePath.toString(),descriptor,visible);

            return new SimpleAnnotationVisitor();
        }

        public void visitTypeInsn(int opcode, java.lang.String type){
            System.out.printf("me\tvisitTypeInsn\t%d\t%s\n",opcode,type);

        }

    }

    static class SimpleModuleVisitor extends ModuleVisitor{
        public SimpleModuleVisitor() {
            super(Opcodes.ASM9);
        }

        public void visitEnd(){
            System.out.printf("mo\tvisitEnd\n");
        }

        public void visitExport(java.lang.String packaze, int access, java.lang.String... modules){
            System.out.printf("mo\tvisitExport	%s	%d",packaze,access);
            if(modules != null) {
                for (int i = 0; i < modules.length; i++) {
                    System.out.printf("\t%s", modules[i]);
                }
            }
            else System.out.printf("\tnull");
            System.out.printf("\n");
        }

        public void visitMainClass(java.lang.String mainClass){
            System.out.printf("mo\tvisitMainClass	%s\n",mainClass);
        }

        public void visitOpen(java.lang.String packaze, int access, java.lang.String... modules){
            System.out.printf("mo\tvisitOpen	%s	%d",packaze,access);
            if(modules != null) {
                for (int i = 0; i < modules.length; i++) {
                    System.out.printf("\t%s", modules[i]);
                }
            }
            else System.out.printf("\tnull");
            System.out.printf("\n");
        }

        public void visitPackage(java.lang.String packaze){
            System.out.printf("mo\tvisitPackage	%s\n",packaze);
        }

        public void visitProvide(java.lang.String service, java.lang.String... providers){
            System.out.printf("mo\tvisitProvide	%s",service);
            if(providers != null) {
                for (int i = 0; i < providers.length; i++) {
                    System.out.printf("\t%s", providers[i]);
                }
            }
            else System.out.printf("\tnull");
            System.out.printf("\n");
        }

        public void visitRequire(java.lang.String module, int access, java.lang.String version){
            System.out.printf("mo\tvisitRequire\t%s\t%d\t%s\n",module,access,version);
        }

        public void visitUse(java.lang.String service){
            System.out.printf("mo\tvisitUse	%s\n",service);
        }

    }

    static class SimpleRecordComponentVisitor extends RecordComponentVisitor{
        public SimpleRecordComponentVisitor() {
            super(Opcodes.ASM9);
        }

        public AnnotationVisitor visitAnnotation(java.lang.String descriptor, boolean visible){
            System.out.printf("re\tvisitMainClass	%s\t%b\n",descriptor,visible);

            return new SimpleAnnotationVisitor();
        }

        public void visitAttribute(Attribute attribute){
            System.out.printf("re\tvisitAttribute	%s\n",attribute.type);
        }

        public void visitEnd(){
            System.out.printf("re\tvisitEnd\n");
        }

        public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, java.lang.String descriptor, boolean visible){
            System.out.printf("re\tvisitTypeAnnotation\t%d\t%s\t%s\t%b\n",typeRef, typePath.toString(), descriptor,visible);
            return new SimpleAnnotationVisitor();
        }

    }


    public static void main(final String[] args) {

        try {
            FileInputStream fis;
            fis = new FileInputStream(args[0]);
            BufferedInputStream bis = new BufferedInputStream(fis);

            byte[] data = new byte[65536];
            int len;

            len = bis.read(data);

            ClassReader reader = new ClassReader(data);
            reader.accept(new SimpleClassVisitor(), 0);


        }
        catch (Exception ex) {
            ex.printStackTrace(System.out);
        }


    }
}
