package com.gmail.osbornroad.model.jpa;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.*;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Operation {

    HPC("HPC", "HPC"),
    LASER("LASER", "LSR"),
    CHAMFERING("CHAMFERING", "CMF"),
    BRUSHING("BRUSHING", "BSH"),
    REDUCTION("REDUCTION", "RED"),
    ENDFORMING("ENDFORMING", "ENF"),
    SUMI_CUTTING("SUMI_CUTTING", "CUT"),
    SUMI_OVEN("SUMI_OVEN", "OVE"),
    BENDING("BENDING", "BND"),
    ASSEMBLY("ASSEMBLY", "ASY"),
    DELIVERY("DELIVERY", "DEL");

    private final String fullName;
    private final String shortName;

    Operation(String fullName, String shortName) {
        this.fullName = fullName;
        this.shortName = shortName;
    }

    public String getName() {
        return name();
    }

    public String getFullName() {
        return fullName;
    }

    public String getShortName() {
        return shortName;
    }

   /* public class OperationSerializer extends StdSerializer {

        public OperationSerializer() {
            super(Operation.class);
        }

        public OperationSerializer(Class t) {
            super(t);
        }

        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("name");
            jsonGenerator.writeString(((Operation)o).name());
            jsonGenerator.writeFieldName("shortName");
            jsonGenerator.writeString(((Operation)o).getShortName());
            jsonGenerator.writeEndObject();
        }
    }*/


    public static Comparator<Operation> operationComparator = Comparator.comparing(obj -> obj.ordinal());

    public static List<Operation> getOperationList() {
        List<Operation> allOperationList = new ArrayList<>();
        allOperationList.addAll(Arrays.asList(Operation.values()));
        Collections.sort(allOperationList, operationComparator);
        return allOperationList;
    }

    public static String getOperationsArray() {
        List<Operation> allOperationList = getOperationList();
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for(int i = 0; i < allOperationList.size(); i++){
            sb.append("\"").append(allOperationList.get(i).toString()).append("\"");
            if(i + 1 < allOperationList.size()){
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}

/*
import com.fasterxml.jackson.annotation.JsonIgnore;

import static javax.persistence.GenerationType.IDENTITY;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="operations")
public class Operation extends BaseEntity {

    protected Integer id;
    protected String name;
    private Integer operationSequence;
    private Set<Part> partSet = new HashSet<>();

    public Operation() {
    }

    public Operation(String name, Integer operationSequence) {
        super(name);
        this.operationSequence = operationSequence;
    }

    public Operation(Integer id, String name, Integer operationSequence) {
        super(id, name);
        this.operationSequence = operationSequence;
    }

    @Column(name="operation_sequence")
    public Integer getOperationSequence() {
        return operationSequence;
    }

    public void setOperationSequence(Integer operationSequence) {
        this.operationSequence = operationSequence;
    }

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "part_operation_detail",
            joinColumns = @JoinColumn(name = "operation_id"),
            inverseJoinColumns = @JoinColumn(name = "part_id"))
    public Set<Part> getPartSet() {
        return partSet;
    }

    public void setPartSet(Set<Part> partSet) {
        this.partSet = partSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Operation operation = (Operation) o;

        if (id != null ? !id.equals(operation.id) : operation.id != null) return false;
        if (name != null ? !name.equals(operation.name) : operation.name != null) return false;
        return operationSequence != null ? operationSequence.equals(operation.operationSequence) : operation.operationSequence == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (operationSequence != null ? operationSequence.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", operationSequence=" + operationSequence +
                '}';
    }
}
*/
