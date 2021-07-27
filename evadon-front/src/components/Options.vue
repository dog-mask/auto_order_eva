<template>
    <div>
    <b-table striped dark hover :items="items" :fields="fields">
        <template #cell(mody_quantity)>
            <b-row>
                <b-col>
                    <b-button size="sm" class="mr-2" @click="addOnedayNeed">
                        +
                    </b-button>
                </b-col>
                <b-col>
                    <b-button size="sm" class="mr-2" @click="popOnedayNeed">
                        -
                    </b-button>
                </b-col>
            </b-row>
        </template>
    </b-table>   
    </div>
</template>

<script>
import {getAllProduct,popOnedayNeed,addOnedayNeed} from '../service'
export default {
    
    name: 'AllProduct',
    
    data(){
        return{
            fields:[
                {
                    key: 'id',
                    label: 'index'
                },
                {
                    key: 'name',
                    label: '품명'
                },
                {
                    key: 'onedayNeed',
                    label: '일사용량'
                },
                {
                    key: 'mody_quantity',
                    label: '재고수정'
                }
            ],
            items: [],
        }
    },
    async created(){
        const ret = await getAllProduct()
        this.items = ret.data
    },
    methods: {
        popOnedayNeed(event){
            const items = this.items
            const index = event.target.parentNode.parentNode.parentNode.parentNode.children[0].innerText
            const onedayNeed = event.target.parentNode.parentNode.parentNode.parentNode.children[2]
            const selectedRow = items[index-1]

            const res = popOnedayNeed(selectedRow)
            if(res<0){
                alert("실패")
            }else{
               let quantity = Number(onedayNeed.innerText)
               if(quantity>0){
               onedayNeed.innerText=quantity-1
               }
            }
        },
        addOnedayNeed(event){
            const items = this.items
            const index = event.target.parentNode.parentNode.parentNode.parentNode.children[0].innerText
            const onedayNeed = event.target.parentNode.parentNode.parentNode.parentNode.children[2]
            const selectedRow = items[index-1]

            const res = addOnedayNeed(selectedRow)
            if(res<0){
                alert("실패")
            }else{
               let quantity = Number(onedayNeed.innerText)
               onedayNeed.innerText=quantity+1
            }

        }
    }
    
}
</script>